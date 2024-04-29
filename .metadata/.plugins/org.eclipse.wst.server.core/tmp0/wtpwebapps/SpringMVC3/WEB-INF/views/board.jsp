<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 기본적으로 jstl/el 을 쓴다 레거시 프로젝트에는 기본적으로 jstl 라이브러리가 담아져있다 
고로 로드하는 코드만 적어준다 메이븐 dependencies 폴더를 안을 보면 jstl 라이브러리가 보인다 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="cpath" value="${pageContext.request.contextPath}" />
<!-- == controller가져온 것임  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- 자동완성 기능을 위해 가져온  jquery-ui  -->
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<link rel = "stylesheet" href="https://code.jquery.com/ui/1.13.1/themes/smoothness/jquery-ui.css">

<!-- 챠트를 그리기 위한 라이브러리  -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<!-- 챠트를 그리기 plugins  -->
<script src= "https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2 "></script>
</head>

<body>
	<!-- 이 jsp를 화면에 보이게 하려면 스프링의 ds(디스패쳐서블릿)는 항상 controller 이라는
 클래스를 실행시키게 되어있으므로 개발자는 controller클래스를 생성해야 한다 -->
	<div class="jumbotron">
		<h1>빅데이터 23차 게시판</h1>
		<p>Bootstrap을 사용하여 게시판 만들자</p>
	</div>

	<div class="container">
		<div class="card">
			<div class="card-header">게시판 연습</div>
			<div class="card-body">

				<form id="searchForm" onsubmit="return false" class="form-inline">
					<div align="right" class="form-group">
						<select name="type" class="form-control btn-warning" id="sel1">
							<option class="btn-light">작성자</option>
							<option class="btn-light">제목</option>
						</select>
					</div>
					<input id = "auto_complete" name="text" type="text" class="form-control">
					<input id="searchBtn" type="submit" class="btn btn-warning" value="검색">

				</form>
				<br>
				<table id="myTable" class="table table-bodered table-hover">
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>게시글</td>
						<td>작성일</td>
					</tr>
					<!-- jstl/el 을 사용하여 model 안에 저장된 게시글 정보를 전부 화면에 출력 -->
					<c:forEach items="${list}" var="b">
						<tr>
							<td>${b.idx}</td>
							<td>
								<!-- 1.쿼리 스트링으로 데이터 보내기 
						     2. 경로상에 그냥 바로 데이터 포함해서 보내기--> <!-- <a href="${cpath}/boardContent?idx=${b.idx}">${b.title}</a> -->
								<a href="${cpath}/boardContent/${b.idx}">${b.title}</a>
							</td>
							<td>${b.writer}</td>
							<td>${b.content}</td>
							<td>${b.inDate}</td>
						</tr>
					</c:forEach>
				</table>
			<!-- onclick은 get 전송방식 -->
			<button onclick="location.href='${cpath}/register'" class="btn btn-primary btn-sm">글쓰기</button>
			<!-- chartBtn은 비동기를 위한 class -->
			<button class="btn btn-success btn-sm chartBtn">챠트보기</button>
			</div>	
			
			<div class="card-footer myChartArea" >
				<h1>트위터 조회수</h1>
				<canvas id = "myChart" width="500" height="500" ></canvas>	
			</div>
		</div>
		<br></br>
	
	</div>
	<script type="text/javascript">
		// cpath는 자바코드이므로 따로 빼준다.
		var cpath = "${cpath}";
	</script>
<!-- 챠트 그리기 ----------------------------------------- -->
<script type="text/javascript">

//1. 첫 실행시 myChartArea 영역을 숨겨놓기
// $(".myChartArea").css("display" , none); //매개변수2개면 변경 1개면 가지고 온다
 $(".myChartArea").hide(); //footer를 숨기는 것임

// 2.챠트보기 누르면 myChartArea 영역을 보여주기 --> footer을 보여주기
$(".chartBtn").on("click",function(){
	// myChartArea영역이 숨겨져 있다면 영역을 보여주기
	if ($(".myChartArea").css("display")=="none") {
	$(".myChartArea").slideDown(); // slideDown() --> 제이쿼리의 기능
	// 비동기 통신으로 /artist url 로 데이터 요청
	// --> 언급된 빈도수가 가장 높은 상위5명에 대한
	// --> 아티스트명과 빈도수 데이터를 결과값으로 받아와서 챠트를 그려주세요
	 	$.ajax({  	
		url : `${cpath}/artist`,
		// data : data, 보내줄 데이터는 없음 받아오기만 함
		type : "get",
		dataType : "json",
		success: function(res) {
			console.log("받아온 결과값 >> " ,res);
			// 라벨, 데이터 생성
			var	labels =[];
			var	datas =[];
			for(var i = 0; i<res.length;i++){
				labels.push(res[i].artist.toUpperCase());
				datas.push(res[i].keywordCnt);
			}
			drawChart(labels,datas);
			},
			error :function(e){
			console.log(e);
		}
	})
	}else{
	// 캔버스를 삭제하고 
	$("#myChart").remove();
	// 다시 새로운 캔버스 추가하기
	$(".myChartArea").append('<canvas id = "myChart" width="500" height="500" ></canvas>');
	// 그렇지 않으면 영역을 위로 올리면서 숨겨주기
	$(".myChartArea").slideUp();
	}
	
})

//함수로 쪼개기
function drawChart (labels,datas){
	// 1. 챠트를 그릴 canvas태그를 선택
	// const ctx = document.getElementById('myChart');
	var ctx = $("#myChart");
	// config가 데이터를 선언하므로 위쪽에 data을 정의
	const data = {
			  // db에서 조회한 아티스트명으로 변경
			  labels: labels,
			
			  datasets: [{
			    label: '트위터조회수',
			    // db에서 조회한 키워드빈도수로 변경
			    data: datas,
			    backgroundColor: [
			      //'rgb(255, 99, 132)',
			      //'rgb(54, 162, 235)',
			     // 'rgb(255, 205, 86)',
			      //'rgb(255, 205, 90)',
			     // 'rgb(255, 205, 95)'
			      '#8ecae6', // css문법상 #을 붙여컬러지정
			      '#ff006e',
			      '#bde0fe',
			      '#ff70a6',
			      '#080708'
			    ],
			    hoverOffset: 4
			  }]
			};
	const options = {
		// 챠트크기를 상대적인 크기가 아니라 내가 원하는 크기로 변경
		// position : relative 속성을 풀어주는 방법
		// 위의 width="500" height="500"이 적용됨
		responsive : false,
		// 따로 설치한 플러그인 사용하자
		plugins : {
			datalabels : {
				color : [
					"#fff",
					"#fff",
					"#fff",
					"#fff",
					"#fff"
				],
				// 형식을 기능적으로 동작 // v,c : 밸류(조회수) ,컨텍스트(object)
				formatter : function(v,c){
					// v,c가 의미하는 건 뭘까?
					console.log("f확인>>",v);
					console.log("c확인>>",c);
					// 챠트 종류마다 구조가 다름
					// c출력해서 구조 확인해보기
					return c.chart.data.labels[c.dataIndex]
				},
				font :{
					size : 20	
				}
			}		
		}	
	};
	const config = {
			// 플러그인 의 종류가 여러개 있음
			// --> 객체를 넘겨주기 
			  plugins : [ChartDataLabels],
			  // 챠트 종류 지정
			  type: 'pie',
			  // 들어갈 데이터 지정  // 속성 : 변수
			  data: data,
			  // 
			  options : options
			};
	// 2. 챠트그리기
	// new Chart (챠트영역 , 객체형식의 챠트의 옵션들)
	new Chart(ctx,config);		
}
</script>
    
	<script src="resources/js/myfirstjs.js" ></script>



</body>
</html>