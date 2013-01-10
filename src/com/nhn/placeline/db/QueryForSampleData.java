/*
sample query for member table (완성)

insert into member (userId,userName,userPhone) values ('abc123','김에이','010-1111-2666')
insert into member (userId,userName,userPhone) values ('abc234','김이','010-2222-2666')
insert into member (userId,userName,userPhone) values ('abc345','최씨','010-3333-2666')
insert into member (userId,userName,userPhone) values ('abc456','박디','010-4444-2666')
insert into member (userId,userName,userPhone) values ('abc567','고에프','010-5555-2666')
insert into member (userId,userName,userPhone) values ('abc678','홍쥐','010-6666-2666')
insert into member (userId,userName,userPhone) values ('abc789','한아이','010-7777-2666')

sample query for group table (완성)

insert into group(groupId,groupName,groupCreator,groupCustom) values ('1','네이버고등학교5기','abc123','2')
insert into group(groupId,groupName,groupCreator,groupCustom) values ('2','NHN신입사원교육','abc345','1')
insert into group(groupId,groupName,groupCreator,groupCustom) values ('3','라인동호회','abc678','3')

sample query for pin table (시간, 좌표값 및 컨텐트만 빼고 완성)

insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values ('1','서울근처',CURRENT_TIMESTAMP,'100','100','임시본문','1')
insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values ('2','서울근처',CURRENT_TIMESTAMP,'100','100','임시본문','2')
insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values ('3','서울근처',CURRENT_TIMESTAMP,'100','100','임시본문','3')
insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values ('4','서울근처',CURRENT_TIMESTAMP,'100','100','임시본문','4')
insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values ('5','올레길탐방',CURRENT_TIMESTAMP,'100','100','임시본문','5')
insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values ('6','올레길탐방',CURRENT_TIMESTAMP,'100','100','임시본문','6')
insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values ('7','올레길탐방',CURRENT_TIMESTAMP,'100','100','임시본문','7')
insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values ('8','올레길탐방',CURRENT_TIMESTAMP,'100','100','임시본문','8')
insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values ('9','서울근처',CURRENT_TIMESTAMP,'100','100','임시본문','9')
insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values ('10','대전근처',CURRENT_TIMESTAMP,'100','100','임시본문','10')
insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values ('11','대구근처',CURRENT_TIMESTAMP,'100','100','임시본문','11')
insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values ('12','광주근처',CURRENT_TIMESTAMP,'100','100','임시본문','12')
insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values ('13','부산근처',CURRENT_TIMESTAMP,'100','100','임시본문','13')
insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values ('14','제주근처',CURRENT_TIMESTAMP,'100','100','임시본문','14')

sample query for userIdToGroupId table (완성)

insert into userIdToGroupId(userId,groupId) values ('abc123','1')
insert into userIdToGroupId(userId,groupId) values ('abc345','1')
insert into userIdToGroupId(userId,groupId) values ('abc456','1')
insert into userIdToGroupId(userId,groupId) values ('abc123','2')
insert into userIdToGroupId(userId,groupId) values ('abc234','2')
insert into userIdToGroupId(userId,groupId) values ('abc567','2')
insert into userIdToGroupId(userId,groupId) values ('abc678','2')
insert into userIdToGroupId(userId,groupId) values ('abc123','3')
insert into userIdToGroupId(userId,groupId) values ('abc456','3')
insert into userIdToGroupId(userId,groupId) values ('abc678','3')
insert into userIdToGroupId(userId,groupId) values ('abc789','3')

sample query for pinToPicture table (미완성)

insert into pinIdToPicture(pinId,picture) values ('1',null)

sample query for pinToReply table (미완성)

insert into pinIdToPicture(pinId,replyDate,replyCreator,replyContent) values ('1','CURRENT_TIMESTAMP,'abc123','첫번째 댓글은 내가 담!')


*/
