package com.osf.test.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.osf.test.service.PBoardService2;
import com.osf.test.service.impl.PBoardServiceImpl2;
import com.osf.test.vo.PhotoBoardVO;

public class PBoardServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String savePath = "D:\\study\\workspace\\osf-jsp\\WebContent\\upload";
	private PBoardService2 pbs = new PBoardServiceImpl2();

	// 나중에 할 때는 이 경로가 필요없다. 톰캣에서 제공하는 리얼 경로를 사용한다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	String uri = request.getRequestURI();
	uri = uri.replace("/pboard2/", "");
	if("list".equals(uri)) {
		request.setAttribute("pBoardList",pbs.selectPBoardList());
		RequestDispatcher rd = request.getRequestDispatcher("/views/photo-board2/list.jsp");
		rd.forward(request,response);
		return; 
	}else {
		try {
			int pbNum = Integer.parseInt(uri);
			System.out.println(pbs.selectPBoard(pbNum));
			request.setAttribute("pBoard",pbs.selectPBoard(pbNum));
			RequestDispatcher rd = request.getRequestDispatcher("/views/photo-board2/view.jsp");
			rd.forward(request,response);
			return; 
		}catch(NumberFormatException e){
			throw new ServletException("상세조회는 번호조회만 가능합니다.");
		}
	}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.replace("/pboard2/", "");
		if ("insert".equals(uri)) {
			DiskFileItemFactory dfiFactory = new DiskFileItemFactory(); //commons-fileupload-1.3.3.jar 에 있는 아이
			String tmpPath = System.getProperty("java.io.tmpdir");
			File tmpFile = new File(tmpPath);
			dfiFactory.setRepository(tmpFile);
//    	dfiFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			dfiFactory.setSizeThreshold(10 * 1024 * 1024); // 얼마나 저장할지 정해준다.(바이트 단위/하드디스크에서는 1MB를 1000KB로 계산한다.)

			ServletFileUpload sfu = new ServletFileUpload(dfiFactory); // 파싱하기 위한 객채
			sfu.setSizeMax(20 * 1024 * 1024); // 여기도 바이트 -> 전체 사이즈 / 사이즈는 서버 생각하면서 해야지?
			sfu.setFileSizeMax(20 * 1024 * 1024); // -> 파일 하나 사이즈 /우리는 파일 하나니까 같이 맞춰도 괜찮다.
			try {
				List<FileItem> fileList = sfu.parseRequest(request); //parseRequest=>request를 분석해서 FileItem List를 반환해준다.
				Map<String, String> pBoard = new HashMap<>();
				for (int i = 0; i < fileList.size(); i++) {
					FileItem fi = fileList.get(i);
					if (fi.isFormField()) {
						pBoard.put(fi.getFieldName(), fi.getString("utf-8")); //utf-8은 여기에 하드코딩 하는 것 보다 application scope영역이나 static으로 저장해서 한곳을 바라보게 하는것이 더 효율적이다.
					} else {
						String rFileName = fi.getName();
						String extName = rFileName.substring(rFileName.lastIndexOf(".")+1);
						String fileName = System.currentTimeMillis()+"";
						File saveFile = new File(savePath + "\\" + fileName +"."+ extName);
						pBoard.put("pb_real_path",rFileName);
						pBoard.put("pb_file_path","/upload/"+fileName+"."+extName); //AbsolutePath=>절대경로
						fi.write(saveFile);
					}
				}
				PhotoBoardVO pb = new PhotoBoardVO();
				pb.setPbTitle(pBoard.get("pb_title"));
				pb.setPbContent(pBoard.get("pb_Content"));
				pb.setPbRealPath(pBoard.get("pb_real_path"));
				pb.setPbFilePath(pBoard.get("pb_file_path"));
				if(pbs.insertPBoard(pb)==1) {
					request.setAttribute("msg", "집중해라!!!");
					request.setAttribute("url", "/views/photo-board2/insert.jsp");
					RequestDispatcher rd = request.getRequestDispatcher("/views/result.jsp");
					rd.forward(request, response);
					return;
				}else {
					
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("update".equals(uri)) {

		} else if ("delete".equals(uri)) {

		} else {

		}
	}

	public static void main(String[] args) {
	}
}
