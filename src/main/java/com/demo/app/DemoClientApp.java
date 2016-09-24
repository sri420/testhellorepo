package com.demo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@SpringBootApplication
public class DemoClientApp {
	
	@Autowired
	DemoRepository demoRepository;

	@Autowired
	DemoHelper demoHelper;
	 
	public static void main(String[] args) {
		SpringApplication.run(DemoClientApp.class, args);
	}
	
	@RequestMapping(value = "/demo", method = RequestMethod.POST)
	public void saveDemo3(@RequestBody DemoRequest demoRequest) {
		Demo demo=new Demo();
		demo.setDemoId(demoRequest.getDemoId());
		demoRepository.save(demo);
	}
	@RequestMapping(value = "/demo/{id}", method = RequestMethod.GET)
	public DemoResponse getDemo(@PathVariable("id") String id) throws Exception {
	
		

		DemoResponse demoResponse=null;
		Demo demo = demoRepository.findOne(id);

		if (null != demo && demo.getDemoId().trim().length() > 0) {
			demoResponse= demoHelper.getDemoResponse(demo);
		
			return demoResponse;
		} else {
			
			throw new Exception("DemoId not found in the System");
		}


	}
	/*@RequestMapping(value = "/democlient", method = RequestMethod.POST)
	public void saveDemo(@RequestBody DemoRequest demoRequest) {

		AsyncRestTemplate asycTemp = new AsyncRestTemplate();
		Map<String,Object> requestMap=new HashMap<>();
		requestMap.put("startDate", "2016-09-16");
		requestMap.put("startTime", "16:00");
		requestMap.put("endTime", "21:00");
		requestMap.put("demoId", "100");
		HttpEntity<Map<String,Object>> request = new HttpEntity<>(requestMap);
		
		ListenableFuture<ResponseEntity<DemoResponse>> futureEntity   =asycTemp.
				  exchange("http://localhost:8080/demo", HttpMethod.POST, request, DemoResponse.class);

	    futureEntity
	            .addCallback(new ListenableFutureCallback<ResponseEntity<DemoResponse>>() {
	                @Override
	                public void onSuccess(ResponseEntity<DemoResponse> result) {
	                   System.out.println("Response received (async callable)");
	                    System.out.println(result.getBody());
	                    // Need assertions
	                }
	 
	                @Override
	                public void onFailure(Throwable t) {
	                	 System.out.println("Error");
	                	 System.err.println(t);
	                }
	            });
	 
	}
	@RequestMapping(value = "/democlient2", method = RequestMethod.POST)
	public void saveDemo2(@RequestBody DemoRequest demoRequest) {

		AsyncRestTemplate asycTemp = new AsyncRestTemplate();
		
		
		HttpEntity<DemoRequest> request = new HttpEntity<>(demoRequest);
		
		ListenableFuture<ResponseEntity<Void>> futureEntity   =asycTemp.
				  exchange("http://localhost:8080/noreturndemo", HttpMethod.POST, request, Void.class);

	    futureEntity
	            .addCallback(new ListenableFutureCallback<ResponseEntity<Void>>() {
	                @Override
	                public void onSuccess(ResponseEntity<Void> result) {
	                   System.out.println("Response received (async callable)");
	                    System.out.println(result.getBody());
	                    // Need assertions
	                }
	 
	                @Override
	                public void onFailure(Throwable t) {
	                	 System.out.println("Error");
	                	 System.err.println(t);
	                }
	            });
	 
	}*/
	
}