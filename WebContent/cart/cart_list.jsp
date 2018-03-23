<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>当当图书 – 全球最大的中文网上书店</title>
<link href="../css/book.css" rel="stylesheet" type="text/css" />
<link href="../css/second.css" rel="stylesheet" type="text/css" />
<link href="../css/secBook_Show.css" rel="stylesheet" type="text/css" />
<link href="../css/shopping_vehicle.css" rel="stylesheet"
	type="text/css" />
<script src="../js/jquery-1.4.3.js" type="text/javascript"></script>
<script src="../js/cart_list.js" type="text/javascript"></script>
<script type="text/javascript">
			
</script>
</head>
<body>
	<br />
	<br />
	<div class="my_shopping">
		<img class="pic_shop" src="../images/pic_myshopping.gif" />

	</div>
	<div id="div_choice" class="choice_merch">
		<h2 id="cart_tips">您已选购以下商品</h2>
		<div class="choice_bord">
			<table class="tabl_buy" id="tbCartItemsNormal">
				<tr class="tabl_buy_title">
					<td class="buy_td_6"><span>&nbsp;</span></td>
					<td><span class="span_w1">商品名</span></td>
					<td class="buy_td_5"><span class="span_w2">市场价</span></td>
					<td class="buy_td_4"><span class="span_w3">当当价</span></td>
					<td class="buy_td_1"><span class="span_w2">数量</span></td>
					<td class="buy_td_2"><span>变更数量</span></td>
					<td class="buy_td_1"><span>删除</span></td>
				</tr>
				<tr class='objhide' over="no">
					<td colspan="8">&nbsp;</td>
				</tr>

				<!-- 购物列表开始 -->
<c:set var="deletedFlag" value="0"></c:set>
<c:set var="noItemFlag" value="1" ></c:set>
				<c:forEach var="item" items="${cart.items }">
					<c:choose>
					<c:when test="${item.deleted == 0  }">
				<c:set var="noItemFlag" value="0" ></c:set>
					<tr class='td_no_bord'>
						<td style='display: none'><span
							id="item_id_${item.productId}">${item.productId }</span> </td>
						<td class="buy_td_6"><span class="objhide"><img /> </span></td>
						<td><a href="#">${item.productName }</a></td>
						<td class="buy_td_5"><span class="c_gray">¥
								${item.fixedPrice }</span></td>
						<td class="buy_td_4">&nbsp;&nbsp; <span>￥${item.dangPrice }</span>
						</td>
						<td class="buy_td_1">&nbsp;&nbsp;<span id="product_num_${item.productId}">${item.productNum }</span>
						</td>
						<td><input id="txt_change_item_num_${item.productId }" class="del_num" onblur="txt_item_change_num(${item.productNum});"
							type="text" size="3" maxlength="4" />
							 <a href="javascript:change_item_num(${item.productId },txt_item_change_num(${item.productId}))">变更</a>
						</td>
						<td><a id="delete_item" href="javascript:change_item_status(${item.productId },1)">删除</a></td>
					</tr>
					</c:when>
					<c:otherwise>
					<c:set var="deletedFlag" value="1"></c:set>
					</c:otherwise>
					</c:choose>
				</c:forEach>
				<!-- 购物列表结束 -->
			</table>
			
			<c:choose>
			<c:when test="${noItemFlag == 1 }">
			<div id="div_no_choice" >
				
				<div class="no_select">您还没有挑选商品</div><a style="position:relative;
left:850px;" href="../main/main.jsp">>>前往当当首页</a>
			</div>
			</c:when>
			
			<c:otherwise>
			<div class="choice_balance">
				<div class="select_merch">
					<a href='../main/main.jsp'> 继续挑选商品>></a>
				</div>
				<div class="total_balance">
					<div class="save_total">
						您共节省： <span class="c_red"> ￥<span id="total_economy">
								<fmt:formatNumber type="number" value="${cart.totalSave+0.000001 }"
									pattern="0.00"></fmt:formatNumber>
						</span>
						</span> <span id='total_vip_economy' class='objhide'> ( 其中享有优惠： <span
							class="c_red"> ￥<span id='span_vip_economy'>0.00</span>
						</span> )
						</span> <span style="font-size: 14px">|</span> <span class="t_add">商品金额总计：</span>
						<span class="c_red_b"> ￥<span id='total_account' ><fmt:formatNumber
									type="number" value="${cart.totalPrice+0.000001 }"  pattern="0.00"></fmt:formatNumber></span>
						</span>
					</div>
					<div id="balance" class="balance">
						<a name='checkout' href="cart_settle.action"> <img
							src="../images/butt_balance.gif" alt="结算" border="0" title="结算" />
						</a>
					</div>
				</div>
			</div>
			</c:otherwise>
			</c:choose>
		</div>
	</div>

	<!-- 用户删除恢复区 -->

<c:choose>
<c:when test="${deletedFlag == 1 }">
	<div id="divCartItemsRemoved" class="del_merch">
		<div class="del_title">您已删除以下商品，如果想重新购买，请点击“恢复”</div>
		<table class="tabl_del"  id="del_table">
			<tbody>

				<c:forEach var="item" items="${cart.items }">
				<c:choose>
				<c:when test="${item.deleted == 1  }">
				
				<tr>
					<td width="58" class="buy_td_6">&nbsp;</td>
					<td width="365" class="t2"><a href="#">${item.productName }</a></td>
					<td width="106" class="buy_td_5">￥${item.fixedPrice }</td>
					<td width="134" class="buy_td_4"><span>￥${item.dangPrice }</span></td>
					<td width="56" class="buy_td_1"><a href="javascript:change_item_status(${item.productId },0)">恢复</a></td>
					<td width="16" class="objhide">&nbsp;</td>
				</tr>
				</c:when>
				</c:choose>
				</c:forEach>


				<tr class="td_add_bord">
					<td colspan="8">&nbsp;</td>
				</tr>


			</tbody>
		</table>
	</div>
	</c:when>
</c:choose>
	<br />
	<br />
	<br />
	<br />
	<!--页尾开始 -->
	<%@include file="../common/foot.jsp"%>
	<!--页尾结束 -->
</body>
</html>



