var cmtFrmElem = document.querySelector('#cmtFrm');
var cmtListElem = document.querySelector('#cmtList');
// 참조값, 주소값을 알고 있으면 이래저래 부릴 수 있다.

var cmtModModalElem = document.querySelector('#modal');

function regCmt(){
	var cmtVal = cmtFrmElem.cmt.value;	//. = 속성명으로 접근(id나 name)

	var param = {
		iboard: cmtListElem.dataset.iboard, // data-iboard 속성의 값
		cmt: cmtVal // 사용자가 입력하는 댓글이 여기로 들어옴
	};
	regAjax(param);
}
//서버에게 등록해줘~!
function regAjax(param){
	const init = {
		method: 'POST',
		body: new URLSearchParams(param) //URLSearchParams : 서블릿에서 파라미터로 받을 수 있도록 해주는 것
	};
		// cmtInsSel : 서블렛주소
	fetch('cmtInsSel', init)
	.then(function(res){
		return res.json();
	})
	.then(function(myJson){ //서버에서 나한테 응답으로 준 값 ???????
		console.log(myJson);
		
		switch(myJson.result){
			case 0: //등록 실패
			alert('등록 실패');
			break;
			case 1: //등록 성공
			cmtFrmElem.cmt.value = ''; // 처음에 적었던 댓글을 지워주는 역할
			getListAjax();
			break;
		}
	});
}

//서버에세 댓글 리스트 자료 달라고 요청하는 함수
function getListAjax(){
	var iboard = cmtListElem.dataset.iboard;
	
	fetch('cmtInsSel?iboard=' + iboard) //설정값이 없으므로 get방식 (a태그 생각하기! 쿼리스트링)
	.then(function(res){
		return res.json();
	})
	.then(function(myJson){
		console.log(myJson);
		
		makeCmtElemList(myJson);
	});
	
}

function makeCmtElemList(data){
	
	cmtListElem.innerHTML = '';
	// =innerText/ 내용 비우기, 댓글 새로달면 테이블이 또 생기기때문에 비우고 새 댓글만 존재하도록
	
	var tableElem = document.createElement('table');
	var trElemTitle = document.createElement('tr');
	var thElemCtnt = document.createElement('th');
	var thElemWriter = document.createElement('th');
	var thElemRegdate = document.createElement('th');
	var thElemBigo = document.createElement('th');
	
	thElemCtnt.innerText = '내용'; //th가 tr밖에 존재
	thElemWriter.innerText = '작성자';
	thElemRegdate.innerText = '작성일';
	thElemBigo.innerText = '비고';
	
	trElemTitle.append(thElemCtnt); //th들이 tr사이에
	trElemTitle.append(thElemWriter);
	trElemTitle.append(thElemRegdate);
	trElemTitle.append(thElemBigo);
	
	tableElem.append(trElemTitle); //tr이 table안에
	cmtListElem.append(tableElem); //table을 div안에 위치하게 됨
	
	var loginUserPk = cmtListElem.dataset.login_user_pk;
	
	data.forEach(function(item){
		var trElemCtnt = document.createElement('tr');
		var tdElem1 = document.createElement('td');
		var tdElem2 = document.createElement('td');
		var tdElem3 = document.createElement('td');
		var tdElem4 = document.createElement('td');
		
		tdElem1.append(item.cmt);
		tdElem2.append(item.writerNm); //대소문자 가림
		tdElem3.append(item.regdate);
	
		if(parseInt(loginUserPk) === item.iuser){
			var delBtn = document.createElement('button');
			var modBtn = document.createElement('button');
			
			//삭제버튼 클릭시
			delBtn.addEventListener('click', function(){
				if(confirm('삭제하시겠습니까?')){
					delAjax(item.icmt);}	
			});
			
			//수정버튼 클릭시
			modBtn.addEventListener('click', function(){
				//댓글,수정 모달창 띄우기	
				openModModal(item);	
			});
			
			delBtn.innerText = '삭제';
			modBtn.innerText = '수정';
			
			tdElem4.append(delBtn);
			tdElem4.append(modBtn);
			
		}
		trElemCtnt.append(tdElem1);
		trElemCtnt.append(tdElem2);
		trElemCtnt.append(tdElem3);
		trElemCtnt.append(tdElem4);
		
		tableElem.append(trElemCtnt);
	});
	
	}

function delAjax(icmt){
	fetch('cmtDelUpd?icmt=' + icmt)
	.then(function(res){
		return res.json();
	})
	.then(function(data){
		console.log(data);
		
		switch(data.result){
			case 0:
			alert('댓글 삭제를 실패하였습니다.');
			break;
			case 1:
			getListAjax();
			break;
			
		}
	});
	
}

//댓글수정
function modAjax(){
	var cmtModFrmElem = document.querySelector('#cmtModFrm');
	var param = {
		icmt : cmtModFrmElem.icmt.value,
		cmt : cmtModFrmElem.cmt.value
}
	const init = {
		method: 'POST',
		body: new URLSearchParams(param) //URLSearchParams객체가 servlet에서 getparam할수있도록 해준다.
	};
	fetch('cmtDelUpd', init) // servlet명, 설정값 / 설정값이 없으면 get방식이 기본
	.then(function(res){
		return res.json(); // 객체
	})
	.then(function(myJson){	
		if(myJson.mod == 1){
			getListAjax();
			closeModModal();
		}else{
			alert('댓글 수정을 실패하였습니다.');
		}
	});
}

function openModModal({icmt, cmt}){
	cmtModModalElem.className = '';
	
	var cmtModFrmElem = document.querySelector('#cmtModFrm');
	cmtModFrmElem.icmt.value = icmt;
	cmtModFrmElem.cmt.value = cmt;
}
function closeModModal(){
	cmtModModalElem.className = 'displayNone';
}

//getListAjax(); //이 파일이 임포트되면 함수 1회 호출