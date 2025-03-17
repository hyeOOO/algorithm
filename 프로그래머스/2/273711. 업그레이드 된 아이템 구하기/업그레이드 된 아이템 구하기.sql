-- 코드를 작성해주세요
SELECT I.ITEM_ID
        ,ITEM_NAME
        ,RARITY
FROM ITEM_INFO I INNER JOIN ITEM_TREE IT
ON I.ITEM_ID = IT.ITEM_ID
WHERE PARENT_ITEM_ID IN (
                            SELECT ITEM_ID
                            FROM ITEM_INFO
                            WHERE RARITY = 'RARE'
                        )
ORDER BY ITEM_ID DESC;