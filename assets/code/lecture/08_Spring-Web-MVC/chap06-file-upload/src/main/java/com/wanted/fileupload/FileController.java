package com.wanted.fileupload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileController {

    // .ymal 에 지정한 환경변수 불러오는 방법
    @Value("${file.upload-dir}")
    private String uploadDir;

    /* comment.
    *   1. 사용자가 업로드한 파일/설명 확인
    *   2. 파일 저장 경로 확인 및 파일 저장 경로가 없다면 생성
    *   3. 원본 파일명을 UUID 기반의 새로운 이름으로 변경
    *   4. 생성된 경로에 파일 저장
    *   5. 결과 메세지와 파일 저장 위치 URL 을 Model 에 담아 응답
    * */

    @PostMapping("single-file")
    public ModelAndView single(@RequestParam MultipartFile singleFile,
                               @RequestParam String description,
                               ModelAndView mv)
    {
        System.out.println("singleFile = " + singleFile);
        System.out.println("description = " + description);

        // 서버측에서 파일 저장 시 이름 변수
        try {
            String savedName = save(singleFile);
            mv.addObject("message", "파일 업로드 성공!!!");
            mv.addObject("files", "/uploads/" + savedName);
        } catch (IOException e) {
            mv.addObject("message", "파일 업로드 실패.." + e.getMessage());
        }

        mv.setViewName("result");

        return mv;
    }

    @PostMapping("multi-file")
    public ModelAndView multiFileUpload(@RequestParam("multiFile") List<MultipartFile> multipartFile, String description, ModelAndView mv) {

        System.out.println("multipartFile = " + multipartFile);
        System.out.println("description = " + description);

        // 여러 개의 파일을 처리하기 위한 DTO 객체 활용
        List<FileDTO> files = new ArrayList<>();
        // 화면에서 접근할 URL 저장용
        List<String> savedFiles = new ArrayList<>();

        try {
            // 전달 받은 여러 개의 파일을 반복
            for (MultipartFile file : multipartFile) {
                String savedName = save(file);
                files.add(new FileDTO(file.getOriginalFilename(), savedName, uploadDir, description));
                savedFiles.add("/uploads/" + savedName);
            }

            mv.addObject("message", "멀티 파일 업로드 성공!");
            mv.addObject("files", savedFiles);
        } catch (Exception e) {

            // 예를 들어 5개 파일을 넣었는데 4개는 정상적으로 저장되고, 1개만 안되면?
            // ex) Transaction 의 rollback 처럼 동작하게 만들 것이다.
            // 실패 시에 이전에 저장된 파일을 삭제
            for (FileDTO file : files) {
                new File(new File(uploadDir).getAbsoluteFile(), file.getSavedName()).delete();
            }
            mv.addObject("message", "멀티 파일 업로드 실패!");
        }

        mv.setViewName("result");

        return mv;
    }

    // 파일을 저장하고, 저장된 파일의 이름을 반환한다.
    private String save(MultipartFile file) throws IOException {

        // 파일의 원본 이름 추출 // test.png
        String originName = file.getOriginalFilename();
        System.out.println("originName = " + originName);

        // 파일의 확장자 분리   // test / .png  / 대문자를 소문자로 변경 (.PNG -> .png)
        String ext = originName.substring(originName.lastIndexOf(".")).toLowerCase();

        // 중복되는 파일명 방지를 위한 UUID
        // 스크린샷.png -> 34687543453.png
        String savedName = UUID.randomUUID().toString() + ext;
        
        // 파일을 저장할 폴더 생성
        File dir = new File(uploadDir).getAbsoluteFile();
        dir.mkdirs();

        // 업로드 파일을 변환 할 준비
        // 업로드한 파일을 생성한 디렉터리에 저장할 이름으로 변환
        file.transferTo(new File(dir, savedName));

        return savedName;

        
    }



}
