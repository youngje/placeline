package com.nhn.placeline.dao;

//import com.nhn.placeline.Activity.R;

public class SampleData {

/*
	query for member table of sample data(완성)	

	"insert into member (userId,userName,userPhone) values (?,'김에이','010-1111-2666','"+R.drawable.user_1+"')"
	"insert into member (userId,userName,userPhone) values (?,'김이','010-2222-2666','"+R.drawable.user_2+"')"
	"insert into member (userId,userName,userPhone) values (?,'최씨','010-3333-2666','"+R.drawable.user_3+"')"
	"insert into member (userId,userName,userPhone) values (?,'박디','010-4444-2666','"+R.drawable.user_4+"')"
	"insert into member (userId,userName,userPhone) values (?,'고에프','010-5555-2666','"+R.drawable.user_5+"')"
	"insert into member (userId,userName,userPhone) values (?,'홍쥐','010-6666-2666','"+R.drawable.user_6+"')"
	"insert into member (userId,userName,userPhone) values (?,'한아이','010-7777-2666','"+R.drawable.user_7+"')"

	
	query for group table of sample data(완성)	
	
	"insert into group(groupId,groupName,groupCreator,groupCustom) values (?,'매력학과5기','1','"+R.drawable.group_map_image_1+"')"
	"insert into group(groupId,groupName,groupCreator,groupCustom) values (?,'NHN신입교육','2','"+R.drawable.group_map_image_2+"')"
	"insert into group(groupId,groupName,groupCreator,groupCustom) values (?,'라인동호회','6','"+R.drawable.group_map_image_3+"')"


	query for group table of sample data(핀제목, 핀본문, 좌표값 보완 필요함)

	"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'서울근처A',CURRENT_TIMESTAMP,'100','100','임시본문','1')"
	"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'서울근처B',CURRENT_TIMESTAMP,'100','100','임시본문','1')"
	"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'서울근처C',CURRENT_TIMESTAMP,'100','100','임시본문','1')"
	"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'서울근처D',CURRENT_TIMESTAMP,'100','100','임시본문','1')"
	"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'올레길탐방A',CURRENT_TIMESTAMP,'100','100','임시본문','2')"
	"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'올레길탐방B',CURRENT_TIMESTAMP,'100','100','임시본문','2')"
	"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'올레길탐방C',CURRENT_TIMESTAMP,'100','100','임시본문','2')"
	"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'올레길탐방D',CURRENT_TIMESTAMP,'100','100','임시본문','2')"
	"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'서울근처',CURRENT_TIMESTAMP,'100','100','임시본문','3')"
	"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'대전근처',CURRENT_TIMESTAMP,'100','100','임시본문','3')"
	"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'대구근처',CURRENT_TIMESTAMP,'100','100','임시본문','3')"
	"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'광주근처',CURRENT_TIMESTAMP,'100','100','임시본문','3')"
	"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'부산근처',CURRENT_TIMESTAMP,'100','100','임시본문','3')"
	"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'제주근처',CURRENT_TIMESTAMP,'100','100','임시본문','3')"


	query for reply table of sample data(미완성-없어도 되지만 많이 있을 수록 좋은..?)
	 
	(매력학과5기 그룹의 핀들에 대한 댓글들)
	"insert into pin(pinId,pinName,pinDate,pinX,pinY,pinContent,groupId) values (?,'1',CURRENT_TIMESTAMP,'3','굿굿')"

	(NHN신입교육 그룹의 핀들에 대한 댓글들)

	(라인동호회 그룹의 핀들에 대한 댓글들)


	query for UserIdToGroupId table of sample data(완성)

	"insert into userIdToGroupId(userId,groupId) values ('1','1')"
	"insert into userIdToGroupId(userId,groupId) values ('3','1')"
	"insert into userIdToGroupId(userId,groupId) values ('4','1')"
	"insert into userIdToGroupId(userId,groupId) values ('1','2')"
	"insert into userIdToGroupId(userId,groupId) values ('2','2')"
	"insert into userIdToGroupId(userId,groupId) values ('5','2')"
	"insert into userIdToGroupId(userId,groupId) values ('6','2')"
	"insert into userIdToGroupId(userId,groupId) values ('1','3')"
	"insert into userIdToGroupId(userId,groupId) values ('4','3')"
	"insert into userIdToGroupId(userId,groupId) values ('6','3')"
	"insert into userIdToGroupId(userId,groupId) values ('7','3')"
	
	
	query for pinIdToPicture table of sample data(미완성-사진파일의 형식에 대하여 미확정)
    
    "insert into pinIdToPicture(pinId, picture) values ('"+pin.getPinId()+"', '"+picture+"')"
	
*/
}
