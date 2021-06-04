var frmElem = document.querySelector('#frm'); //css와 같은방법
// frm.uid해도 되긴 함
//document는 전수조사
var uidElem = frmElem.uid;
var upwElem = frmElem.upw;
var chkUpwElem = frmElem.chkUpw;
var unmElem = frmElem.unm;
var chkUidResultElem = frmElem.querySelector('#chkUidResult');
//(자식) id값, name값으로 접근가능
//id와 name중 우선순위 : id ?
//Elem 주소값을 안다는 것

var btnChkIdElem = frmElem.btnChkId; //중복ID체크 버튼
//var btnChkIdElem = frmElem.querySelector('#btnChkId');
btnChkIdElem.addEventListener('click', function(){
	idChkAjax(uidElem.value);
});
//value값을 idChkAjax에 보냄

function idChkAjax(uid){
	console.log(uid);
	chkUidResultElem.innerText = '이 아이디는 사용할 수 있습니다.';
	fetch('/user/idChk?uid=' + uid) //fetch : get방식으로 호출
	.then(function(res){
		return res.json();
	})
	.then(function(myJson){
		console.log(myJson);
		switch(myJson.result){
			case 0:
				chkUidResultElem.innerText = '이 아이디는 사용할 수 있습니다.';
			break;
			case 1:
				chkUidResultElem.innerText = '이 아이디는 사용할 수 없습니다.';
			break;
		}
	});
	}
	
//onclick은 (html)jsp에서 바로 연결하는거랑 똑같음
//addEventListener


function frmChk(){
	//var uidVal = document.getElementByName('uid');
	var uidVal = uidElem.value; //참조값.value (=속성값을 달라!)
	
	//아이디 하나도 안 적으면 alert "아이디를 작성해주세요." false 리턴
	//2자 이하면 alert "아이디는 3자이상 작성해주세요." false 리턴
	
	if(uidVal.length == 0){
		alert('아이디를 작성해주세요.');
		return false;
	}else if(uidVal.length<=2){
		alert('아이디는 3자이상 작성해주세요.');
		return false;
	}
	//이상이 생기면 return false; (false하면 post안날라감)
	
	var upwVal = upwElem.value;
	var chkUpwVal = chkUpwElem.value;
	//비밓번호 하나도 안 적으면 alert "비밀번호를 작성해주세요." false 리턴
	//3자 이하면 alert "비밀번호는 4자이상 작성해주세요." false 리턴
	//비밀번호와 확인비밀번호가 다르면 "비밀번호를 확인해주세요." false리턴
	
	
	if(upwVal.length <3){
		if(upwVal.length==0){
		alert('비밀번호를 작성해주세요.');
	}else{
		alert('비밀번호는 4자이상 작성해주세요.');
		
	}return false;
	}else if(upwVal !== chkUpwVal){ // 자바만 equals 씀
		alert("비밀번호를 확인해주세요.");
		return false;
	}
	
	if(unmElem.value.length < 2){
		alert('이름은 2자이상 작성해주세요.');
		return false;
	}
}