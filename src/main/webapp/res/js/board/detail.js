var cmtFrmElem = document.querySelector('#cmtFrm');
var cmtListElem = document.querySelector('#cmtList');
// 참조값, 주소값을 알고 있으면 이래저래 부릴 수 있다.

var cmtModModalElem = document.querySelector('#modal');
function regCmt(){
	var cmtVal = cmtFrmElem.cmt.value;	//. = 속성명으로 접근(속성명이 없을 경우 자식 요소의 id나 name)
	var param = {
		iboard: cmtListElem.dataset.iboard, // data-iboard 속성의 값
		cmt: cmtVal // 사용자가 입력하는 댓글이 여기로 들어옴
	};
	regAjax(param);
}

//서버에게 등록해줘~!
function regAjax(param){
	const init = { //javascript객체
		method: 'POST',
		body: JSON.stringify(param),
		//new URLSearchParams(param) : (java에서는) 서블릿에서 get파라미터로 받을 수 있도록 해주는 것
		headers:{
			'accept' : 'application/json', //json으로 날린다고 알려줘야함
			'content-type' : 'application/json;charset=UTF-8'
		}
	}

	// cmt : 서블렛주소
	.then(function(myJson){ //여기가 객체가 됨
	fetch('cmt', init)
	.then(function(res){ //서버에서 나한테 응답으로 준 값 (return한것이 res로 감)
		return res.json(); //여기까지 아직 문자열인데 json을 통해서 객체로 넘어옴
	})
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
	fetch('cmt/' + iboard) // 설정값이 없으므로 get 방식 (a태그 생각하기! 쿼리스트링)
		// controller에서 실행 (= response.sendRedirect)
		// promise 동기 <-> 서버한테 통신 날리는 fetch
		// 통신은 무조건 비동기로 처리 why? 통신하는게 컴퓨터 입장에서는 느림
		// 기다렸다가 화면 만들면 느리니까 비동기처리 하는 것 / 통신된다면! then이 응답 하는 순서 
	.then(function(res){ // then 쓰면 비동기 / fetch와 then이 관계는 동기
		return res.json(); // .json으로 거치면서 js에서 쓸 수 있는 언어로 바뀜
	})
	.then(function(myJson){ //댓글들의 정보가 js 객체로 되어있음
		console.log(myJson);
		makeCmtElemList(myJson); //함수호출
	});
}

// 동기 : 1하는일이 끝나기 전에 2를 실행하지 않음
// 비동기 : 걍 실행
function makeCmtElemList(data){ //함수정의
	cmtListElem.innerHTML = '';
	//cmtListElem.innerText = ''; &lt와 $gt로 바꿔주기 때문에 태그가 그대로 찍힌다
	//cmtListElem.append(); <-> 앞에 붙일 때는 prepend / (문자열) 입력
	//cmtListElem.appendChild(); (로드[element]객체 주소값)을 넣어야 한다.
	// =은 교체를 의미한다. / append는 메소드로 교체가 아닌 추가하는 개념임
	// = innerText/ 내용 비우기, 댓글 새로달면 테이블이 또 생기기때문에 비우고 새 댓글만 존재하도록
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

	//data 배열이거나 컬렉션 자료형일 때 forEach 가능
	data.forEach(function(item){ //여기부터 forEach문 시작
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
				//if에는 boolean으로 true,false만 들어간다.
				//문자열 > 빈 문자열이면 js에선 false로 인식한다.
				//숫자 > 0만 false 나머지는 다 true
				if(confirm('삭제하시겠습니까?')){
					delAjax(item.icmt);}	//item : 댓글 하나하나의 정보가 들어있음
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

	}); //여기까지 forEach문(비동기) 돌림 (가지고 있는 item수만큼)
}
function delAjax(icmt){	//js객체
	fetch('cmt/' + icmt, {method:'DELETE'})
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
		cmt : cmtModFrmElem.modCmt.value
}
	const init = {
		method: 'PUT', //수정
		body: JSON.stringify(param),
			// new URLSearchParams(param) //URLSearchParams객체가 servlet에서 getparam할수있도록 해준다.
		headers: {
			'accept' : 'application/json', //json으로 날린다고 알려줘야함
			'content-type' : 'application/json;charset=UTF-8'
		}
	};
	fetch('cmt', init) // servlet명, 설정값 / 설정값이 없으면 get방식이 기본
	.then(function(res){
		return res.json(); // 객체
	})
	.then(function(myJson){
		closeModModal();
		switch(myJson.result){
			case 0:
				alert('댓글 수정을 실패하였습니다.');
			break;
			case 1:
				getListAjax();
			break;
		}
	});
}
function openModModal({icmt, cmt}){
	cmtModModalElem.className = '';
	var cmtModFrmElem = document.querySelector('#cmtModFrm');

	cmtModFrmElem.icmt.value = icmt; //여기서 value는 사용자가 적은 글이 값이 됨
	cmtModFrmElem.modCmt.value = cmt;
}
function closeModModal(){
	cmtModModalElem.className = 'displayNone';
}

var favIconElem = document.querySelector('#favIcon');
favIconElem.addEventListener('click', function(){
	if(favIconElem.classList.contains('far')){ // x -> ㅇ
		insFavAjax();
	}else{ //ㅇ -> x
		delFavAjax();
	}
});

//좋아요 처리
function insFavAjax(){
	const param = {
		iboard: cmtListElem.dataset.iboard
	};
	const init = {
	method: 'POST',
	body: JSON.stringify(param),
	headers:{
		'accept' : 'application/json',
		'content-type' : 'application/json;charset=UTF-8'
	}
};
	fetch('fav', init)
		.then(function(res){
		return res.json();
		})
		.then(function (myJson){
			if(myJson.result === 1){
			toggleFav(1);
		}
})
}

//좋아요 취소
function delFavAjax(){
	const init = {
		method: 'DELETE',
	}
	const iboard = cmtListElem.dataset.iboard;

	fetch('fav?iboard=' + iboard, init)
		.then(function(res){
			return res.json();
		})
		.then(function (myJson){
			if(myJson.result === 1){
				toggleFav(0);
			}
		})
}

//좋아요 여부 값 가져오기
function getFavAjax() {
	fetch('fav?iboard=' + cmtListElem.dataset.iboard)
		.then(function (res) {
			return res.json();
		})
		.then(function (myJson) {
			toggleFav(myJson.result);
		});
}

function toggleFav(toggle){
			switch(toggle) {
				case 0: //좋아요 x
					favIconElem.classList.remove('fas');
					favIconElem.classList.add('far');
					break;
				case 1: //좋아요 ㅇ
					favIconElem.classList.remove('far');
					favIconElem.classList.add('fas');
					break;
			}
}

getListAjax(); //이 파일이 임포트되면 함수 1회 호출
getFavAjax();