$(function () {
           $(".wenjian").on("mouseover",function () {
                $(this).children(".xuankuang").show();
            })
            $(".wenjian").on("mouseleave",function () {
                $(this).children(".xuankuang").hide();
            })
          /*
			 * $(".wenjian").hover(function () {
			 * $(this).children(".xuankuang").show(); },function () {
			 * $(this).children(".xuankuang").hide(); })
			 */
            $(".xuank").click(function () {
                $(this).parent().parent().toggleClass("asas", "asas3")
            })

            $(".dingbu3").mouseover(function () {
                $(".dingbudingwei").show();
            })
            $(".dingbu3").mouseleave(function () {
                $(".dingbudingwei").hide();
            })
            $(".dingbudingwei").mouseover(function () {
                $(".dingbudingwei").show();
            })
            $(".dingbudingwei").mouseleave(function () {
                $(".dingbudingwei").hide();
            })

            $(".gn8").mouseover(function () {
                $(".yincanggn8").show();
            })
            $(".gn8").mouseleave(function () {
                $(".yincanggn8").hide();
            })
            $(".yincanggn8").mouseover(function () {
                $(".yincanggn8").show();
            })
            $(".yincanggn8").mouseleave(function () {
                $(".yincanggn8").hide();
            })

            $(".gn8-1").mouseover(function () {
                $(".yincanggn8-1").show();
            })
            $(".gn8-1").mouseleave(function () {
                $(".yincanggn8-1").hide();
            })
            $(".yincanggn8-1").mouseover(function () {
                $(".yincanggn8-1").show();
            })
            $(".yincanggn8-1").mouseleave(function () {
                $(".yincanggn8-1").hide();
            })

            $(".gn8-2").mouseover(function () {
                $(".yincanggn8-2").show();
            })
            $(".gn8-2").mouseleave(function () {
                $(".yincanggn8-2").hide();
            })
            $(".yincanggn8-2").mouseover(function () {
                $(".yincanggn8-2").show();
            })
            $(".yincanggn8-2").mouseleave(function () {
                $(".yincanggn8-2").hide();
            })

            $(".gn81").click(function () {
                $(".gn8").hide();
                $(".yincanggn8").hide();
                $(".gn8-1").show();
            })
            $(".gn82").click(function () {
                $(".gn8").hide();
                $(".yincanggn8").hide();
                $(".gn8-2").show();
            })

            $(".gn83").click(function () {
                $(".gn8-1").hide();
                $(".yincanggn8-1").hide();
                $(".gn8").show();
            })
            $(".gn84").click(function () {
                $(".gn8-1").hide();
                $(".yincanggn8-1").hide();
                $(".gn8-2").show();
            })

            $(".gn85").click(function () {
                $(".gn8-2").hide();
                $(".yincanggn8-2").hide();
                $(".gn8").show();
            })
            $(".gn86").click(function () {
                $(".gn8-2").hide();
                $(".yincanggn8-2").hide();
                $(".gn8-1").show();
            })

        })

// 以下为动态加载网页内容及功能实现

// 加载根目录文件
$(function(){
		loadFile(0,"",-1,1,1);
		$(".gn0").hide();
		
		// 主页返回上一页功能
		$(".gn0").click(function(){	
			var id=$("#id").val();
			if(id==0)
				$(".gn0").hide();
			loadFile(id,"",-1,1,1,1);
			getPid(id);
			$("#search").val();// 清空搜索框的值
		})
		
		//创建文件夹
		$("#build").click(function(){
			var can=$("#adddocument");
			// alert(can);
			// if(can==null){
			var pid=$("#pid").val();
			var id=$("#id").val();
			/*alert(pid);*/
			var fileName=prompt("请输入文件名");
			if(fileName==null||fileName==""){
				return;
			}
			$.post("/Cloud_Disk/ResourseNewDocument_Servlet",{"fileName":fileName,"pid":pid},function(res){
			/*	var note='<div class="wenjian"><a href="javascript:getin('+id+','+pid+')"><img src="tupian-yunpan-index/wenjianjia.png">'+
				fileName+'</a><div class="xuankuang"><input type="checkbox" class="xuank" value="'+id+'"></div></div>';*/
				if(res=="double"){
					alert("文件名重复！");
				}else if(res=='yes'){
					/*var pid=$("#id").val();
					var id=$("#pid").val();*/
					/*if(id==0)
						loadFile(0,"",-1,1,1,1);
					else*/
						loadFile(pid,"",-1,1,1,1);
					//$(".wenjianxunhuan").append(note);
				}else
					alert("创建文件失败，请重试！");
			})
				
			// }
		})
		
		//文件上传功能
		$(".gn1").click(function(){
			// alert("上传");
			$("#file1").click();
			$("#file1").change(function(){
				$("#fileForm").submit();
			})
		})
	})
	
	// 获取父级分类
	function getPid(pid){
		$.post("/Cloud_Disk/ResourseGetPidById_Servlet",{'id':pid},function(res){
			if(res!=null){
				$("#id").val(res.pid);
				$("#pid").val(res.id);
			}
		},"json")
	}
	
	function upload(){
		$.post("/Cloud_Disk/ResourseGetPidById_Servlet",{'id':pid},function(res){
			if(res!=null)
				$("#id").val(res.pid);
				$("#pid").val(id);
		},"json")
	}
	
	// 左侧文件分类显示功能
	function fileType(filetype){
		/*alert('sfdsfsd');*/
		$(".gn1").show();
		$(".gn3").show();
		$(".gn4").show();
		$(".gn6").show();
		$("#fileType").val(filetype);
		$(".gn2").hide();
		loadFile(0,"",filetype,1,1,1);
	}
	
	// 进入下级文件夹
	function getin(id,pid){
		loadFile(id,"",-1,1,1,1);
		$(".gn0").show();
		$("#id").val(pid);// 把上级分类作为返回输入的id保存
		$("#pid").val(id);
		/*alert($("#pid").val());*/
		/*alert($("#id").val());*/
	}
	
	// 文件搜索功能
	function search(){
		if($(".gn7 input").val()==null||$(".gn7 input").val()==""){
			alert("请输入文件名");
			return;
		}
		loadFile(-1,$(".gn7 input").val(),-1,1,1,1);
	}
	
	//回收站功能
	function reues(){
		loadFile(0,"",-1,1,1,-1)
		$(".gn2").show();
		$("#build").hide();
		$("#recovery").show();
		$(".gn1").hide();
		$(".gn3").hide();
		$(".gn4").hide();
		$(".gn6").hide();
	}
	
// 加载文件及文件夹显示
	function loadFile(pid,fileName,fileType,pageIndex,orderBy,classify){
		$.post("/Cloud_Disk/ResourseShow_Servlet",
				{'id':pid,'fileName':fileName,'fileType':fileType,'pageIndex':pageIndex,'orderBy':orderBy,"classify":classify},
				function(res){
					
					$(".dingbu32").html('');
					$(".dingbu32").html(res.username);
					$(".dw212").html('');
					$(".dw212").html(res.username);
					
			$(".wenjianxunhuan").html("");// 清空显示的文件
			var list=$(res.list);// 获取查询到的文件集合
			var note='';
			$(list).each(function(i){
				if(list[i].filetype==6||list[i].filetype==2)
					note='<div class="wenjian" ><a href="javascript:getin('+list[i].id+','+list[i].pid+')" >';
				else
					note='<div class="wenjian"><a href="" class="documentUrl">';
				switch(list[i].filetype){
					case 1:// 图片
						note+='<img src="'+list[i].filepath+'">';
						break;
					case 2:// 文档
						note+='<img src="tupian-yunpan-index/wendangtubiao.png">';
						break;
					case 3:// 视频
						note+='<img src="tupian-yunpan-index/shipinwenjian.png">';
						break;
					case 4:// 种子
						note+='<img src="tupian-yunpan-index/tupiantubiao.png">';
						break;
					case 5:// 音乐
						note+='<img src="tupian-yunpan-index/yinyuetubiao.png">';
						break;
					case 6:// 文件夹
						note+='<img src="tupian-yunpan-index/wenjianjia.png">';
						break;
					case 7:// 其他文件
						note+='<img src="tupian-yunpan-index/wangyun.png">';
				}
				note+=list[i].filename+'</a><div class="xuankuang"><input type="checkbox" class="xuank" name="select" value="'+list[i].id+'"></div></div>';
				$(".wenjianxunhuan").append($(note));
			})
		},"json")
	}
	//删除文件功能
	function delt(){
		var check=$("[name='select']");
		var num=0;
		$(check).each(function(i){
			if(check[i].checked){
				var id=$(this).val();
				/*alert(id);*/
				$.post("/Cloud_Disk/ResourseDeleteServlet",{'id':id},function(res){
					/*alert(res);*/
					if(res=="yes")
						$(this).parent().parent().remove();
				},"json")
				$(this).parent().parent().remove();
				// getin($("#pid").val(),$("#id").val(pid))
				num++;
			}
		})
		if(num==0){
			alert("请选择文件！");
		}
	}
	
	// 下载功能
/*
 * function download(){ var check=$("[name='select']"); var num=0;
 * $(check).each(function(i){ if(check[i].checked){ var id=$(this).val();
 * $.post("/Cloud_Disk/ResourseDownload_Servlet",{'id':id},function(res){
 * alert(res); if(res!=null) alert(res); }) num++; } }) if(num==0){
 * alert("请选择文件！"); } }
 */
	function download(){
		var check=$("[name='select']");
		var num=0;
		$(check).each(function(i){
			if(check[i].checked){
				var id=$(this).val();
				/* $("#down").val(id);
				 $("#download").submit();*/
				 var form = document.createElement("form");
				 document.body.appendChild(form);
				 var nameInput = document.createElement("input");
				 var pathInput = document.createElement("input");
				 nameInput.type = "hidden";
				 pathInput.type = "hidden";
				 form.appendChild(nameInput);
				 form.appendChild(pathInput);
				 nameInput.value =id;
				 nameInput.name = "id";
				 pathInput.name = "fileName";
				 form.action = "/Cloud_Disk/ResourseDownload_Servlet";
				 form.submit();
				 num++;
			}
		})
		if(num==0){
			alert("请选择文件！");
		}
	}
	
	// 选取文件夹准备移动
	var mov=new Array();
	var arr=new Array();
	function move(){
		var check=$("[name='select']");
		var num=0;
		$(check).each(function(i){
			if(check[i].checked){
				var id=$(this).val();
				arr.push(id);
				num++;
			}
		})
		if(num==0){
			alert("请选择文件！");
			return
		}
		$(".gn3").hide();
		$(".gn4").hide();
		$(".gn5").hide();
		$("#move").hide();
		$("#moveto").show();
	}
	
	// 确认进行移动功能
	function moveto(){
		var id=$("#pid").val();
		for(var i=0;i<arr.length;i++){
			$.post("/Cloud_Disk/ResourseMoveDocument",{"id":arr[i],"targetId":id},function(res){
				if(res.resourse=='error'){
					alert("不能移动到选中文件夹内部");
					return;
				}
				var pid=$("#id").val();
				var id=$("#pid").val();
				//alert(pid);
				getin(id,pid);
			},"json")
		}
		$(".gn3").show();
		$(".gn4").show();
		$(".gn5").show();
		$("#move").show();
		$("#moveto").hide();
	}
	
	//恢复文件
	function recovery(){
		var check=$("[name='select']");
		var num=0;
		$(check).each(function(i){
			if(check[i].checked){
				var id=$(this).val();
				/*alert(id);*/
				$.post("/Cloud_Disk/ResourseRecovery_Servlet",{'id':id},function(res){
					/*alert(res);*/
					if(res=="yes")
						$(this).parent().parent().remove();
				},"json")
				$(this).parent().parent().remove();
				// getin($("#pid").val(),$("#id").val(pid))
				num++;
			}
		})
		if(num==0){
			alert("请选择文件！");
		}
	}
	
	
	
	
	
	
	
	
	
	
	/*function download() {
	  var check=$("[name='select']");
		var num=0;
		$(check).each(function(i){
			if(check[i].checked){
				var id=$(this).val();
				  var url = '/Cloud_Disk/ResourseDownload_Servlet?id='+id;
				  var xhr = new XMLHttpRequest();
				  xhr.open('GET', url, true);    // 也可以使用POST方式，根据接口
				  xhr.responseType = "blob";  // 返回类型blob
				  // 定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑
				  xhr.onload = function () {
				    // 请求完成
				    if (this.status === 200) {
				      // 返回200
				      var blob = this.response;
				      var reader = new FileReader();
				      reader.readAsDataURL(blob);  // 转换为base64，可以直接放入a表情href
				      reader.onload = function (e) {
				        // 转换完成，创建一个a标签用于下载
				        var a = document.createElement('a');
				        a.download = 'data.xlsx';
				        a.href = e.target.result;
				        $("body").append(a);  // 修复firefox中无法触发click
				        a.click();
				        $(a).remove();
				      }
				    }
				  };
				  // 发送ajax请求
				  xhr.send();
				  num++;
				}
			})
	  if(num==0){
			alert("请选择文件！");
		}
	  
	}*/
