// 페이지가 첫번째로 실행되었을때도 AutoComplete 되게 만들기
	$(function(){
		myAjaxAutoComplete();

	})
	
	
	// selsct 태그의 값이 변경시 이벤트 처리
	$("#sel1").on("change",function () {
		myAjaxAutoComplete();	
	})
	
	// --> 참조기법 $("#sel1").on("chang",myAjaxAutoComplete)
	
	// 비동기 통신을 사용하여 작성자 ,제목 리스트를 전부 조회
	// 요청 경로 : /autocomplete
	function myAjaxAutoComplete(){
		$.ajax({
			url : `${cpath}/autocomplete`,
			// 쿼리 스트링 -->  ?type = 작성자
		    // type --> SearchCriteria클래스
			data : {type : $("#sel1").val()},
			dataType : "json",
			
			success: function(res) {
				console.log("받아온 데이터 >> ", res);
				// res --> writer,title만 있음 --> sql구문
				//객체 배열 --> 문자열 배열로 변경
				// 1. 비어있는 배열 생성 
	  			var sourceList =[];
				// 2.RES의 길이 만큼 반복하면서 안쪽에 있는 writer 데이터만 꺼내서 비어있는 배열에 추가
				for(let i = 0; i < res.length; i++) {
					if (res[i].writer != null){
					sourceList.push(res[i].writer);
					}else{
					sourceList.push(res[i].title);
					}
				}
				myAuto(sourceList);
			},
		
			error :function(e){
				console.log(e);
			}	
		})
	}
/////////////////////////////////////////////////////
	
	// 함수만들기
	function myAuto(sourceList) {
	// jquery-ui 를 사용해서 input 태그 아래쪽에 글자 자동완성 기능 넣어보기
		$("#auto_complete").autocomplete({
		// 1. 자동완성 하고 싶은 목록 --> 원래는 디비에서 가져옴 : 리스트단위로~
		source : sourceList,
		// 2. 최소글자
		minlength : 1, // 한 칸만 써도 자동완성 해줘~
		// 3. 딜레이 시간 0.1초
		delay : 100,
		// 4. 해당요소에 포커싱 되었을때 작동할 함수
		focus : function(e,ui){
			// e =이벤트 ui = 화면구성
			return false;
		}
		
	   })
	}

	// 1. searchBtn 클릭시 이벤트 등록
	$("#searchBtn").on("click",function () {
		
	// 2. id값이 searchForm 안에 있는 데이터를 가져오기	
	var data =$("#searchForm").serialize(); // 병렬 데이터를 직렬로~~
				
	// 3.가져온 데이터 출력하기
	console.log(data);
	
	// 4) 비동기 통신 사용하여 데이터 전송(/search)
	$.ajax({
	     // 백틱으로 변경 --> jsp변수 js로 받아야함
		url : `${cpath}/search`,
		data : data,
		type : "get",
		dataType : "json",
		success: function(res) {
			console.log(res);
			
			
		//연재방식	
        // $("body > div.container > div > div.card-body > table").html(`
        //          <tr id=>
         //            <td>번호</td>
         //            <td>제목</td>
          //           <td>작성자</td>
          //           <td>작성일</td>
          //        </tr>`);
            //   for(let i = 0; i < res.length; i++) {
               //   $("body > div.container > div > div.card-body > table").append(`
              //          <tr>
               //            <td>` + res[i].idx + `</td>
               //            <td>` + res[i].title + `</td>
               //            <td>` + res[i].writer + `</td>
               //            <td>` + res[i].inDate + `</td>
               //         </tr>`);
             // }
            
            // 1. column을 제외한 나머지 행들을 비워주기
            // tr:first-child~tr:  ~은 후행이라는 의미  
            $("#myTable tbody tr:first-child~tr").empty();
             
            //2. res(배열)에 담긴 데이터 개수만큼 반복
             $.each(res,function(i,board){
            	 //3. tr태그를 추가
            	 var tr = `
            	 // 역슬래시 제거해야함 --> 자바코드를 안거치므로~ 학습필요
            		 <tr>
						<td>${board.idx}</td>
						<td>
					    <a href="${cpath}boardContent/${board.idx}">${board.title}</a>
						</td>
						<td>${board.writer}</td>
						<td>${board.content}</td>
						<td>${board.inDate}</td>
					</tr> `
            	$("#myTable tbody").append(tr);
            	  
             })
					
		},
		
		error :function(e){
			console.log(e);
		}
		
	})
	
})
    
   

/**
 * 
 */