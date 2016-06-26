package com.sports.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sports.dao.proxy.TestScoreDaoProxy;
import com.sports.entity.TestScore;

public class SingleFileUpload extends FileUploadBase {
	private FileItem fileItem;
	private String justFileNameString;

	/**
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	public void parseRequest(HttpServletRequest request)
			throws UnsupportedEncodingException {

		DiskFileItemFactory factory = new DiskFileItemFactory();

		factory.setSizeThreshold(sizeThreshold);

		if (repository != null)
			factory.setRepository(repository);

		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setHeaderEncoding(encoding);

		try {
			List<FileItem> items = upload.parseRequest(request);

			for (FileItem item : items) {
				if (item.isFormField()) {
					String fieldName = item.getFieldName();
					String value = item.getString(encoding);
					parameters.put(fieldName, value);
				} else {

					if (!super.isValidFile(item)) {
						continue;
					}

					if (fileItem == null)
						fileItem = item;
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传文件, 调用该方法之前必须先调用 parseRequest(HttpServletRequest request)
	 * 
	 * @param fileName
	 *            完整文件路径
	 * @throws Exception
	 */
	public String upload(String fileName) throws Exception {
		File file = new File(fileName);
		return uploadFile(file);
	}

	/**
	 * 上传文件, 调用该方法之前必须先调用 parseRequest(HttpServletRequest request)
	 * 
	 * @param parent
	 *            存储的目录
	 * @throws Exception
	 */
	public String upload(File parent) throws Exception {
		if (fileItem == null)
			return "";

		// 如果是ie截取最后的文件名
		int index = fileItem.getName().lastIndexOf("\\");
		justFileNameString = fileItem.getName();
		// 这里的处理是为了兼容ie和360 因为使用input type="file"时，ie和chrome的值不一样，ie显示了全路径
		// 因为文件名默认为学生成绩.xls，所以截取8位，也可以对下方的name直接使用"学生成绩.xls"
		if (index > -1) {
			justFileNameString = fileItem.getName().substring(index + 1,
					fileItem.getName().length());
		}
		File file = new File(parent, justFileNameString);
		return uploadFile(file);
	}

	private String uploadFile(File file) throws Exception {

		if (fileItem == null)
			return "";

		long fileSize = fileItem.getSize();
		if (sizeMax > -1 && fileSize > super.sizeMax) {
			String message = String
					.format("the request was rejected because its size (%1$s) exceeds the configured maximum (%2$s)",
							fileSize, super.sizeMax);

			throw new org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException(
					message, fileSize, super.sizeMax);
		}
		fileItem.write(file);

		// 文件上传后执行成绩覆盖操作
		String inf = "成功导入0条数据";
		int successCount = 0;
		InputStream fis = new FileInputStream("C:\\upload\\"
				+ justFileNameString);
		List<TestScore> tsList = TransferScore.getAllByPoi(fis);

		TestScoreDaoProxy tsdp1 = null;
		TestScoreDaoProxy tsdp2 = null;
		TestScoreDaoProxy tsdp3 = null;
		for (TestScore ts : tsList) {
			try {
				tsdp1 = new TestScoreDaoProxy();
				if (tsdp1.findByTestTermIdAndStuNumber(ts.getTestTermId(),
						ts.getStuNumber()) == null) {
					tsdp2 = new TestScoreDaoProxy();
					successCount += tsdp2.doCreate(ts);
				} else {
					tsdp3 = new TestScoreDaoProxy();

					successCount += tsdp3.doPartUpdate(ts.getTestTermId(),
							ts.getStuNumber(), ts);
					// System.out.println(successCount);
				}
				fis.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		inf = "成功导入" + successCount + "条数据";
		return inf;
	}

	/**
	 * 获取文件信息 必须先调用 parseRequest(HttpServletRequest request)
	 * 
	 * @return
	 */
	public FileItem getFileItem() {
		return fileItem;
	}
}