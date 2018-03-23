<%@page import="com.tarena.entity.Product"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2>
	<span class="more"><a href="#" target="_blank">更多&gt;&gt;</a> </span>最新上架图书
</h2>
<div class="book_c_04">
	<%-- <%= request.getAttribute("products")
	%> --%>

	<!--最新上架图书开始-->
	<c:forEach var="p"  items="${products }">
	
		<div class="second_d_wai">

			<div class="img">
				<a href="#" target='_blank'><img
					src="../productImages/${p.productPic }" border=0 /> </a>
			</div>
			<div class="shuming">
				<a href="#" target="_blank">${p.productName }</a><a href="#"
					target="_blank"></a>
			</div>
			<div class="price">定价：￥${p.fixedPrice }</div>
			<div class="price">当当价：￥${p.dangPrice }</div>
		</div>
		<div class="book_c_xy_long"></div>
	</c:forEach>
	<!--最新上架图书结束-->

</div>
<div class="clear"></div>