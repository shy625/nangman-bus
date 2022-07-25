package com.nangman.common.exception.handler;

/*
 * 
 * SPA처리를 위한 ControllerAdvice.
 * 요청에 해당하는 Request Mapping이 존재하지 않을 경우 Default로 index.html을 렌더링한다.
 * 
 */
//
//@ControllerAdvice
//public class NotFoundHandler {
//	@Value("${spa.default-file}")
//	String defaultFile;
//
//	@ExceptionHandler(NoHandlerFoundException.class)
//	public ResponseEntity<String> renderDefaultPage(NoHandlerFoundException ex) {
//		String url = ex.getRequestURL();
//		if(url.startsWith("/api/")) {
//			return ResponseEntity.notFound().build();
//		}else {
//			try {
//				ClassPathResource classPathResource = new ClassPathResource(defaultFile);
//				InputStream inputStream = classPathResource.getInputStream();
//    				String body = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
//			    return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(body);
//			} catch (IOException e) {
//				e.printStackTrace();
//				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error completing the action.");
//			}
//		}
//	}
//}
