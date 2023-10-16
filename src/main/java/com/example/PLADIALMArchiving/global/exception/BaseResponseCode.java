package com.example.PLADIALMArchiving.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum BaseResponseCode {

    SUCCESS("S0001", HttpStatus.OK, "요청에 성공했습니다."),


    BAD_REQUEST("G0001", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    NO_ATUTHENTIFICATION("G0002", HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    // User
    USER_NOT_FOUND("U0001", HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    ROLE_NOT_FOUND("U0013", HttpStatus.NOT_FOUND, "역할을 찾을 수 없습니다."),

    // Token
    NULL_TOKEN("T0001", HttpStatus.UNAUTHORIZED, "토큰 값을 입력해주세요."),
    INVALID_TOKEN("T0002", HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰 값입니다."),
    UNSUPPORTED_TOKEN("T0003", HttpStatus.UNAUTHORIZED, "잘못된 형식의 토큰 값입니다."),
    MALFORMED_TOKEN("T0004", HttpStatus.UNAUTHORIZED, "잘못된 구조의 토큰 값입니다."),
    EXPIRED_TOKEN("T0005", HttpStatus.FORBIDDEN, "만료된 토큰 값입니다."),
    NOT_ACCESS_HEADER("T0006", HttpStatus.INTERNAL_SERVER_ERROR, "헤더에 접근할 수 없습니다."),
    BLACKLIST_TOKEN("T0007", HttpStatus.FORBIDDEN, "로그아웃 혹은 회원 탈퇴된 토큰입니다."),

    // Booking
    DATE_OR_TIME_IS_NULL("B0001", HttpStatus.BAD_REQUEST, "날짜와 시간을 모두 입력해주세요."),
    MEMO_SIZE_OVER("B0002", HttpStatus.BAD_REQUEST, "요청사항은 30자 이하로 작성해주세요."),
    START_TIME_MUST_BE_IN_FRONT("B0003", HttpStatus.BAD_REQUEST, "시작시간보다 끝나는 시간이 더 앞에 있습니다."),
    DATE_MUST_BE_THE_FUTURE("B0004", HttpStatus.BAD_REQUEST, "미래의 날짜를 선택해주세요."),
    ALREADY_BOOKED_TIME("B0005", HttpStatus.CONFLICT, "이미 예약되어 있는 시간입니다."),
    BOOKING_NOT_FOUND("B0006", HttpStatus.NOT_FOUND, "존재하지 않는 예약입니다."),
    ALREADY_CANCELED_BOOKING("B0007", HttpStatus.CONFLICT, "이미 취소된 예약입니다."),
    ALREADY_FINISHED_BOOKING("B0008", HttpStatus.CONFLICT, "이미 사용이 완료된 예약입니다."),

    // Office
    OFFICE_NOT_FOUND("O0001", HttpStatus.NOT_FOUND, "존재하지 않는 회의실입니다."),

    // Archiving
    ALREADY_REGISTERED_PROJECT("P0001", HttpStatus.BAD_REQUEST, "이미 등록된 프로젝트입니다."),
    PROJECT_NOT_FOUND("P0002", HttpStatus.NOT_FOUND, "존재하지 않는 프로젝트입니다."),


    MATERIAL_NOT_FOUND("P0004", HttpStatus.NOT_FOUND, "존재하지 않는 자료입니다."),
    UNAUTHORIZED_USER("P0005", HttpStatus.UNAUTHORIZED, "관리자 계정 또는 자료를 업로드한 유저가 아니므로 자료를 삭제할 수 없습니다."),
    ;

    public final String code;
    public final HttpStatus status;
    public final String message;

    public static BaseResponseCode findByCode(String code) {
        return Arrays.stream(BaseResponseCode.values())
                .filter(b -> b.getCode().equals(code))
                .findAny().orElseThrow(() -> new BaseException(BAD_REQUEST));
    }

}
