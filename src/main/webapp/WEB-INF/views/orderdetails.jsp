<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="content-wrapper">
<section class="content-header">
      		<h1>
        		Dashboard
        		<small>Order Detail</small>
     		 </h1>
      		<ol class="breadcrumb">
        		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        		<li class="active"><i class="fa fa-cubes"></i>Orders Overview</li>
        		<li class="active"><i class="fa fa-cubes"></i>Order Details</li>
      		</ol>
</section>
<section class="invoice">
<c:forEach var="orderHeader" items="${oderDetails}" end="0">
     
      <!-- title row -->
      <div class="row">
        <div class="col-xs-12">
          <h2 class="page-header">
            <i class="fa fa-globe"></i> Order: <b>#${orderHeader.orderHdr.refno}</b> 
            <small class="pull-right">Date: ${orderHeader.orderHdr.orderDate}</small>
          </h2>
        </div>

        <!-- /.col -->
      </div>
      <!-- info row -->
      
      <div class="row invoice-info">
        <div class="col-sm-8 invoice-col">
          <h2 class="invoice-client mrg10T">Customer Information:</h2>
          <%-- <address>
            <strong>Customer Name </strong>${orderXml.orderHeader.address.name}<br>
            <strong>Delivery Address </strong>${orderXml.orderHeader.address.address1}<br>
            <strong>Billing Address </strong>${orderXml.orderHeader.address.address1}<br>
            <strong>Telephone Number </strong>${orderXmlorderHdr.address.telefon}<br>
            <strong>Email Address </strong>${orderXml.orderHeader.user.email} 
          </address> --%>
          <form>
          <div class="form-group row">
      		<label class="col-sm-4 col-form-label" >Customer Name</label>
      		<div class="col-sm-4">${orderXml.orderHeader.address.name}</div>
   		 </div>
   		 <div class="form-group row">
      		<label class="col-sm-4 col-form-label" >Delivery Address</label>
      		<div class="col-sm-4">${orderXml.orderHeader.address.address1}</div>
   		 </div>
   		 <div class="form-group row">
      		<label class="col-sm-4 col-form-label">Billing Address</label>
      		<div class="col-sm-4">${orderXml.orderHeader.address.address1}</div>
   		 </div>
   		 <div class="form-group row">
      		<label class="col-sm-4 col-form-label">Email Address</label>
      		<div class="col-sm-4">${orderXml.orderHeader.user.email}</div>
   		 </div>
		</form>
        </div>
        <!-- /.col -->
        <div class="col-sm-4 invoice-col">
        
         <h2 class="invoice-client mrg10T"><b>Order Info</b><br></h2>
         <div class="form-group row">
      		<label class="col-sm-4 col-form-label" >Order ID</label>
      		<div class="col-sm-4">${orderHeader.orderHdr.refno}</div>
   		 </div>
   		 <div class="form-group row">
      		<label class="col-sm-4 col-form-label" >Status</label>
      		<div class="col-sm-4">
      		<c:if test="${ not empty orderHeader.orderHdr.orderStatus }">
      		<span class="label label-warning">${orderHeader.orderHdr.orderStatus}</span>
      		</c:if>
      		<c:if test="${ !not empty orderHeader.orderHdr.orderStatus }">
      		<span class="label label-danger">Denied</span>
      		</c:if>
      		</div>
   		 </div>
   		 <div class="form-group row">
      		<label class="col-sm-4 col-form-label" >Payment Method</label>
      		<div class="col-sm-4">${orderHeader.orderHdr.paymentMethod}</div>
   		 </div>
         
        </div>
        <!-- /.col -->
      </div>
      </c:forEach>
      <div class="divider"></div>

      <!-- Table row -->
      <div class="row">
        <div class="col-xs-12 table-responsive">
          <table class="table table-striped">
            <thead>
            <tr>
              <th>Qty</th>
              <th>Item Code</th>
              <th>invoice type</th>
              <th>Description</th>
              <th>Subtotal</th>
            </tr>
            </thead>
            <c:if test="${ not empty oderDetails }">
            <tbody>
            <c:forEach var="orderDetail" items="${oderDetails}" >
            <tr>
              <td>${orderDetail.quantity}</td>
              <td>${orderDetail.itemcode}</td>
              <td>${orderDetail.invoiceType}</td>
              <td>must me implemented</td>
              <td>${orderDetail.unitprice}</td>
            </tr>
            </c:forEach>
            </tbody>
            </c:if>
          </table>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
</div>