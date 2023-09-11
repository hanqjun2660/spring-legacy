<%--
  Created by IntelliJ IDEA.
  User: b2000
  Date: 2023-09-10
  Time: 오후 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../includes/header.jsp"%>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Board Register</h1>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">Board Read Page</div>
            <div class="panel-body">

                    <div class="form-group">
                        <label>Bno</label>
                        <input class="form-control" name="bno" value='<c:out value="${board.bno}"/>' readonly>
                    </div>
                    <div class="form-group">
                        <label>Title</label>
                        <input class="form-control" name="title" value='<c:out value="${board.title}"/>' readonly>
                    </div>
                    <div class="form-group">
                        <label>TextArea</label>
                        <textarea class="form-control" rows="3" name="content" readonly="readonly"><c:out value="${board.content}"/></textarea>
                    </div>
                    <div class="form-group">
                        <label>Writer</label>
                        <input class="form-control" name="writer" value='<c:out value="${board.writer}"/>' readonly>
                    </div>
                    <button data-oper='modify' class="btn btn-default">Modify</button>
                    <button data-oper='list' class="btn btn-info">List</button>

                <form id="operForm" action="/board/modify" method="get">
                    <input type="hidden" id="bno" name="bno" value='<c:out value="${board.bno}"/>'>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="../includes/footer.jsp"%>

<script type="text/javascript">
    $(document).ready(function() {

        var operForm = $('#operForm');

        $('button[data-oper="modify"]').on('click', function() {
            operForm.append("<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>");
            operForm.attr('action', '/board/modify').submit();
        });

        $('button[data-oper="list"]').on('click', function() {
            operForm.find("#bno").remove();
            operForm.attr('action', '/board/list').submit();
        });
    });
</script>