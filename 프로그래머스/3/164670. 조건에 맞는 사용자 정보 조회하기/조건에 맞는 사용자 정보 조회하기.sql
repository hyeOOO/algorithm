-- 코드를 입력하세요
SELECT U.USER_ID,
        NICKNAME,
        CONCAT(CITY,' ',STREET_ADDRESS1, ' ', STREET_ADDRESS2) AS '전체주소',
        CONCAT(SUBSTR(TLNO,1,3),'-',SUBSTR(TLNO,4,4),'-',SUBSTR(TLNO,8)) AS '전화번호'
FROM USED_GOODS_USER U JOIN (
                                SELECT USER_ID
                                FROM USED_GOODS_BOARD B JOIN USED_GOODS_USER U
                                    ON B.WRITER_ID = U.USER_ID
                                GROUP BY USER_ID
                                HAVING COUNT(BOARD_ID)>=3
                            ) T
    ON U.USER_ID = T.USER_ID
ORDER BY U.USER_ID DESC;