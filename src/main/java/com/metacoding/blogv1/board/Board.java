package com.metacoding.blogv1.board;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor // 풀 생성자
@NoArgsConstructor // 디폴트 생성자
@Table(name = "board_tb") // table 명
@Entity // jpa가 관리
public class Board {
    @Id // pk설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 설정
    private Integer id;
    private String title;
    private String content;
    private Timestamp createdAt;
}
