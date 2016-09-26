package com.hs.eai.monitor.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.hs.eai.monitor.order.model.OrderDetail;
import com.hs.eai.monitor.order.model.OrderHdr;
import com.hs.eai.monitor.project.model.ProjectDetails;
import com.hs.eai.monitor.service.AppUtilsService;
import com.hs.eai.monitor.service.RestClientService;
import com.hs.eai.orderoverview.orderjaxb.Order;

@Controller
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	private static final String REST_URI_ORDERS_BASE = "restUriOrderBase";
	private static final String REST_URI_ORDER_DETAIL = "restUriOrderDetail";
	private static final String REST_URI_ALLE_ORDERS_LazyLoad = "restUriAllOrdersLazyLoad";
	private static final String REST_URI_ORDERS_PER_PAGE_DISPLAY = "restUriAllOrdersLazyLoad";
	private static final Integer totalOrdersPerPageDisplay = 10;
	
	@Autowired
	RestClientService restClientService;
	@Autowired
	AppUtilsService appUtilsService;

	PagedListHolder<OrderHdr> pagedListHolder = null ;
	
	@PostConstruct
	public void init() {
		restClientService.setUriBase(REST_URI_ORDERS_BASE);
	    

	}
    

	/**
	 * 
	 * @param show
	 *            all orders
	 * @param OrderId
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String showorders(Model model, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "maxResult", required = false) Integer maxResult, HttpServletRequest request) {
		
		Integer totalorders = restClientService.countAllorders();
		
		
		Map<String, Integer> params = new HashMap<String, Integer>();
		
		
		if(page == null || page < 1 || maxResult == null){
			page = 1;
			maxResult = restClientService.getLazyLoadMaxResult("orders");
		}
		params.put("startIndex", page);
		params.put("maxResult", maxResult);
		
	
		if (pagedListHolder == null){
			System.out.println("check orders null");
			RestTemplate restTemplate = new RestTemplate();
			String restUriAllordersDetailsazyLoad = restClientService
					.readUriFromProperty(REST_URI_ALLE_ORDERS_LazyLoad);

			ResponseEntity<List<OrderHdr>> ordersResponse = restTemplate.exchange(restUriAllordersDetailsazyLoad,
					HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderHdr>>() {
					}, params);
			System.out.println("call me1: " + System.currentTimeMillis());

			
			pagedListHolder = new PagedListHolder<>(ordersResponse.getBody());
			pagedListHolder.setPageSize(totalOrdersPerPageDisplay);

			model.addAttribute("orders", pagedListHolder.getPageList());
			
		}else if (pagedListHolder.getPageCount() > appUtilsService.getMaxPages(totalorders, maxResult)) {
			System.out.println("check not null orders null");
			RestTemplate restTemplate = new RestTemplate();
			String restUriAllordersDetailsazyLoad = restClientService
					.readUriFromProperty(REST_URI_ALLE_ORDERS_LazyLoad);

			ResponseEntity<List<OrderHdr>> ordersResponse = restTemplate.exchange(restUriAllordersDetailsazyLoad,
					HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderHdr>>() {
					}, params);
			System.out.println("call me2: " + System.currentTimeMillis());
			pagedListHolder = new PagedListHolder<>(ordersResponse.getBody());
			pagedListHolder.setPageSize(maxResult);

			model.addAttribute("orders", pagedListHolder.getPageList());
		} else if (page <= pagedListHolder.getPageCount()) {
			pagedListHolder.setPage(page - 1);
			model.addAttribute("orders", pagedListHolder.getPageList());
		}

		model.addAttribute("maxPages", appUtilsService.getMaxPages(totalorders, maxResult));
		model.addAttribute("totalorders", totalorders);
		model.addAttribute("maxResult", maxResult);
		StringBuilder totalProjMsg = new StringBuilder("Showing ");
		totalProjMsg.append(page).append(" to ").append(maxResult).append(" of ").append(totalorders)
				.append(" entries");
		model.addAttribute("totalordersMessage", totalProjMsg.toString());
		model.addAttribute("orders",pagedListHolder.getPageList());
		
		model.addAttribute("orderRecordsOptions", null);
		return "orders";
	}
	public PagedListHolder<OrderHdr> getPagedListHolder() {
		return pagedListHolder;
	}


	public void setPagedListHolder(PagedListHolder<OrderHdr> pagedListHolder) {
		this.pagedListHolder = pagedListHolder;
	}


	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	public String loadOrders(Model model, @RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "maxResult", required = false) Integer maxResult,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			HttpServletRequest request) {
		
		if(page == null || page < 1 || maxResult == null){
			page = 1;
			maxResult = restClientService.getLazyLoadMaxResult("orders");
		}
		
		
		if(pageSize!= null){
			pagedListHolder.setPageSize(pageSize);
		}
		
		Map<String, Integer> params = new HashMap<String, Integer>();
	    params.put("startIndex", page);
	    params.put("maxResult", maxResult);
	    
	    Integer totalorders = restClientService.countAllorders();
	    System.out.println("page:"+page);
	    System.out.println("page getPageCount():"+pagedListHolder.getPageCount());
	    if (page <= pagedListHolder.getPageCount()) {
	    	System.out.println("kom ik in deze situatie?");
			pagedListHolder.setPage(page - 1);
			model.addAttribute("orders", pagedListHolder.getPageList());
			System.out.println("orders: "+(Integer)pagedListHolder.getPageList().get(0).getId());
		}
	    
	    model.addAttribute("maxPages", appUtilsService.getMaxPages(totalorders, maxResult));
	    model.addAttribute("totalorders", totalorders);
		model.addAttribute("maxResult", maxResult);
		StringBuilder totalProjMsg = new StringBuilder("Showing ");
		totalProjMsg.append(page).append(" to ").append(pagedListHolder.getPageSize()).append(" of ").append(totalorders)
				.append(" entries");
		model.addAttribute("totalordersMessage", totalProjMsg.toString());
	    model.addAttribute("orders",pagedListHolder.getPageList());
	    
			return "ordersTable";
	}
	@RequestMapping(value = "/order_details.html", method = RequestMethod.GET)
	public String showOrderDetails(Model model, @RequestParam(value = "id", required = true) Integer orderId,
			@RequestParam(value = "userId", required = false) String userId) {

		logger.debug("Showing Order detail page..");
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("orderId", orderId);
		String restUriOrderDetail = restClientService.readUriFromProperty(REST_URI_ORDER_DETAIL);

		ResponseEntity<List<OrderDetail>> orderDetailsResponse = restTemplate.exchange(restUriOrderDetail,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDetail>>() {
				}, params);
		List<OrderDetail> orderDetails = orderDetailsResponse.getBody();
		model.addAttribute("oderDetails", orderDetails);

		if (userId != null) {

			// String restUriCutomerSyncByUserId =
			// restClientService.readUriFromProperty(REST_URI_CUSTOMER_SYNC_BY_USER_ID);
			// String
			// restUriCutomerSyncByUserId="http://localhost:8080/TransactionsWS/api/customer/{userId}";
			String restUriOrderXmlByUserId = "http://localhost:8080/TransactionsWS/api/orderXml/{userId}";
			// http://localhost:8080/TransactionsWS/api/customer/{userId}
			ResponseEntity<Order> OrderXMLResponse = restTemplate.exchange(restUriOrderXmlByUserId, HttpMethod.GET,
					null, Order.class, userId);
			// OrderAddressService
			Order orderXml = OrderXMLResponse.getBody();

			if (orderXml != null) {

				model.addAttribute("orderXml", orderXml);
			}

		}
		return "orderdetails";
	}

}
