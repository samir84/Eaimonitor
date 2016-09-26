package com.hs.eai.orderoverview.orderjaxb;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import com.hs.eai.orderoverview.orderjaxb.Order;

public class UnmarshalOrderTest {

	@Test
	public void testUnmarshalLOrderder(){
		UnmarshalLOrder();
	}
	
	public  Order UnmarshalLOrder(){
		
		Order order = null ;
		InputStream is = null;
		File orderFile = null ;
		try{
			
			ClassLoader classLoader = getClass().getClassLoader();
			orderFile = new File(classLoader.getResource("order.xml").getFile());
			is = new ByteArrayInputStream(read(orderFile));
			JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			order = (Order) jaxbUnmarshaller.unmarshal(is);
			Order.OrderHeader orderHeader = order.getOrderHeader();
			Order.OrderHeader.Address address = orderHeader.getAddress();
			System.out.println(address.getAddress1());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return order;
	}
	public byte[] read(File file) throws IOException {
	    

	    byte[] buffer = new byte[(int) file.length()];
	    InputStream ios = null;
	    try {
	        ios = new FileInputStream(file);
	        if (ios.read(buffer) == -1) {
	            throw new IOException(
	                    "EOF reached while trying to read the whole file");
	        }
	    } finally {
	        try {
	            if (ios != null)
	                ios.close();
	        } catch (IOException e) {
	        }
	    }
	    return buffer;
	}
}
