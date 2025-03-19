SELECT ID
        ,FISH_NAME
        ,LENGTH
FROM FISH_INFO FI INNER JOIN FISH_NAME_INFO FN
    ON FI.FISH_TYPE = FN.FISH_TYPE
WHERE (FI.FISH_TYPE, LENGTH) IN (
                                    SELECT FISH_TYPE, MAX(LENGTH)
                                    FROM FISH_INFO
                                    GROUP BY FISH_TYPE
                                )
ORDER BY ID;