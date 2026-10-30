package com.wanted.fileupload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class FileDTO {
    // 아래 3요소 무조건 들어가야 함!!
    private String originFileName;  // 원본 파일명
    private String savedName;       // 저장 파일명
    private String filePath;        // 저장 경로
    private String description;

}
