package com.example.boot_20230427.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.boot_20230427.dto.Member;

@Mapper
public interface MemberMapper {

    // 멤버 회원가입
    public int memberJoin(Member member);

    // 멤버 로그인
    public Member selectMemberOne(Member member);

    // 멤버 수정
    public int updateMemberOne(Member member);

    // 회원탈퇴
    public int deleteMemberOne(Member member);

    // 멤버 로그인 아이디
    public Member selectMemberOne1(String userid);

    // 멤버 비밀번호 변경
    public int updateMemberpassword(Member member);
    
}
