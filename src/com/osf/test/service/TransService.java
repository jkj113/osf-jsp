package com.osf.test.service;

import java.util.Map;

public interface TransService {
	//애가 에러인지 아닌지 그리고 메세지 (에러메세지/번역 된 메세지)
	//isError->alert창 / msg->화면에 보여줘야한다.
	public Map<String,String> transperText(String source, String target, String text);
	

}
