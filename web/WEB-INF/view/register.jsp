<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/13 0013
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/tag/global.jsp"%>
<html>
<head>
    <title>注册账号</title>
    <%--引入validate--%>
    <script src="${jsBasePath}/jquery-3.3.1.min.js" ></script>
    <script src="${jsBasePath}/jquery.validate.js"></script>
    <script>
        // 自定义验证用户名
        $.validator.addMethod( "checkUsername",function(value,element){

            var pattern =/^[a-zA-Z][a-zA-Z0-9]*/;

            if(value !=''){if(!pattern.exec(value)){return false;}};

            return true;

        } ,  "用户名第一个字符必须是英文");
     //检查名字合法性
        $.validator.addMethod( "checkName",function(value,element){

            var pattern =/^[\\u4e00-\\u9fa5a-zA-Z0-9]*/;

            if(value !=''){if(!pattern.exec(value)){return false;}};

            return true;

        } ,  "名字不合法");
       //检查重复密码
        $.validator.addMethod( "checkRePassword",function(value,element){
var psw=$("#password").val();
            if(value==psw) return true;
            return false;
        },"与原密码不一致");
        $(document).ready(function () {
            // 验证格式
      var  validator=$("#register_form").validate({
            rules:{
                username:{
                    required:true,
                    minlength:6,
                    maxlength:16,
                    checkUsername:true,
                    remote:{
                        url:"register_remoteUsername.htm",
                        data:{
                            username:function(){ return $("#username").val()},
                        }
                    }
            },
                password:{
                    required:true,
                    minlength:6,
                    maxlength:16,
                },
                rePassword:{
                    required:true,
                    minlength:6,
                    maxlength:16,
                    checkRePassword:true
                },
                name:{
                    required:true,
                    checkName:true,
                    minlength:2,
                    maxlength:8,
                },
                mail:{
                    required:true,
                    email:true,
                }
        },
          messages:{
              username:{
                  required: "不能为空",
                  minlength: "最少需要6个字符",
                  maxlength: "最大字符为16个",
                  remote:"用户名已存在"
              },
              password:{
                  required:"不能为空",
                  minlength:"最少需要6个字符",
                  maxlength:"最大字符为16个",
              },
              rePassword:{
                  required:"不能为空",
                  minlength:"最少需要6个字符",
                  maxlength:"最大字符为16个",
              },
              name:{
                  required:"不能为空",
                  minlength:"最少2个字符",
                  maxlength:"最多8个字符",
              },
              mail:{
                  required:"不能为空",
                  email:"邮箱格式不对",
              }
          },


        })
        });
    </script>
<style>
    .error{
        color: red;
    }
</style>
</head>
<body>
<jsp:include page="/public/topMenu.htm"></jsp:include>

<div class="register_bg">
    <form id="register_form" action="${basePath}/register_form.htm" method="POST">
        <div id="register_box">
            <div class="register_main">
                <div class="register_row">
                    <div class="form_area">
                        <label class="label">请输入用户名</label>
                        <input class="username" placeholder="请输入用户名或邮箱"
                               type="text" maxlength="64" name="username" id="username" >
                    </div>
                </div>
                <div class="register_row">
                    <div class="form_area">
                        <label class="label">请输入名称</label>
                        <input class="name" placeholder="请输入角色名称" type="text"
                               maxlength="64" name="name" id="name" >
                    </div>
                </div>
                <div class="register_row">
                    <div class="form_area">
                        <label class="label">请输入密码</label>
                        <input type="password" maxlength="64" name="password"
                               id="password">
                    </div>
                    <div class="form_notes">
                        <div id="password_tag" class="password_tag">&nbsp;</div>
                    </div>
                    <div style="clear: left;"></div>
                </div>
                <div class="register_row">
                    <div class="form_area">
                        <label class="label">重复输入密码</label>
                        <input type="password" maxlength="64" name="rePassword"
                               id="rePassword" >
                    </div>
                    <div class="form_notes">
                        <div id="rePassword_tag" class="rePassword_tag">&nbsp;</div>
                    </div>
                    <div style="clear: left;"></div>
                </div>
                <div class="register_row">
                    <div class="form_area">
                        <label class="label">请输入邮箱</label>
                        <input type="email" maxlength="64" name="mail" id="mail" >
                    </div>
                    <div class="form_notes">
                        <div id="mail_tag" class="mail_tag">&nbsp;</div>
                    </div>
                    <div style="clear: left;"></div>
                </div>
            </div>
        </div>
        <input type="submit" class="button button-highlight button-large" value="注册"></input>
    </form>

</div>

</body>
</html>
