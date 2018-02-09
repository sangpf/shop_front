package club.sking.core.utils;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sang
 *
 */
public class FileUtil {
	/**
	 * 上传文件
	 * @param request
	 *            请求对象
	 * @param file
	 *            控制层获取到的文件
	 * @param uploadPath
	 *            文件的路径
	 * @return 上传后的文件信息map
	 */
	public static Map<String, Object> uploadFile(HttpServletRequest request, MultipartFile file, String uploadPath)
			throws IllegalStateException, IOException {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (file.isEmpty()) {
			jsonMap.put("success", false);
			jsonMap.put("msg", "fileEmpty");
			return jsonMap;
		}
		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 获取文件后缀
		String suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
		// 创建相对路径
		String relativePath = "/../data/res/static/" + uploadPath;
		String jdbcPath = "/data/res/static/" + uploadPath;

		String realPath = request.getSession().getServletContext().getRealPath(relativePath);
		if (realPath == null) {
			realPath = request.getSession().getClass().getClassLoader().getResource(relativePath).getPath();
		}

		// 创建文件夹
		File uploadDir = new File(realPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// 将MultipartFile 转成File,并获取MD5值
		CommonsMultipartFile cf = (CommonsMultipartFile) file;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File f = fi.getStoreLocation();
		String md5ByFile = MD5Utils.fileToMD5(f);

		if(md5ByFile == null){
			md5ByFile = System.currentTimeMillis()+"";
		}
		// 上传
		File uploadFile = new File(realPath + "/" + md5ByFile + suffix);
		file.transferTo(uploadFile);

		String url = relativePath + "/" + md5ByFile + suffix;// 文件的相对路径
		String jdbcUrl = jdbcPath + "/" + md5ByFile + suffix;// 文件的存储

		jsonMap.put("success", true);
		jsonMap.put("url", url);
		jsonMap.put("jdbcUrl", jdbcUrl);
		jsonMap.put("realPath", uploadFile.getPath());
		jsonMap.put("file", uploadFile);
		jsonMap.put("md5ByFile", md5ByFile);
		return jsonMap;
	}

	/**
	 * 通过读取文件并获取其width及height的方式，来判断判断当前文件是否图片，这是一种非常简单的方式。
	 *
	 * @param imageFile
	 * @return
	 */
	public static boolean isImage(File imageFile) {
		if (!imageFile.exists()) {
			return false;
		}
		Image img = null;
		try {
			img = ImageIO.read(imageFile);
			if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			img = null;
		}
	}

	/**
	 * 上传 file 文件
	 * @return
	 */
	public static Map<String,Object> uploadZip(MultipartFile file, String uploadPath, HttpServletRequest request){

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		//判断上传的文件是否为空
		if(file.isEmpty()){
			jsonMap.put("success", false);
			jsonMap.put("msg", "上传文件为空");
			return jsonMap;
		}

		//获取上传文件全名
		String originalFilename = file.getOriginalFilename();

		//获取文件后缀
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

		// 将MultipartFile 转成File,并获取MD5值
		CommonsMultipartFile cf = (CommonsMultipartFile) file;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File f = fi.getStoreLocation();
		String md5ByFile = MD5Utils.fileToMD5(f);

		//创建文件要上传到的相对路径
		String relativePath = "/../data/res/static/" + uploadPath;
		//获取相对于服务器的路径
		String realPath = request.getSession().getServletContext().getRealPath(relativePath);

		//创建该相对路径的文件夹 如果不存在则创建
		File uploadDir = new File(realPath);
		if(!uploadDir.exists()){
			uploadDir.mkdirs();
		}

		//创建要上传的文件
		File uploadFile = new File(realPath+"/"+md5ByFile+suffix);

		//执行上传
		try {
			file.transferTo(uploadFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//封装该上传文件的服务器路径
		jsonMap.put("realPath", uploadFile.getPath());

		return jsonMap;

	}

	/**
	 * 获取文件的md5值
	 *
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String getMd5ByFile(File file) throws FileNotFoundException {
		String value = null;
		FileInputStream in = new FileInputStream(file);
		try {
			MappedByteBuffer byteBuffer = in.getChannel().map(
					FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	// 下面这个函数用于将字节数组换成成16进制的字符串
	private static String byteArrayToHex(byte[] byteArray) {
		// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray = new char[byteArray.length * 2];
		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}

	private static String fileMD5(File inputFile) throws IOException {
		// 缓冲区大小（这个可以抽出一个参数）
		int bufferSize = 256 * 1024;
		FileInputStream fileInputStream = null;
		DigestInputStream digestInputStream = null;
		try {
			// 拿到一个MD5转换器（同样，这里可以换成SHA1）
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 使用DigestInputStream
			fileInputStream = new FileInputStream(inputFile);
			digestInputStream = new DigestInputStream(fileInputStream,
					messageDigest);
			// read的过程中进行MD5处理，直到读完文件
			byte[] buffer = new byte[bufferSize];

			while (digestInputStream.read(buffer) > 0)
				;
			// 获取最终的MessageDigest
			messageDigest = digestInputStream.getMessageDigest();
			// 拿到结果，也是字节数组，包含16个元素

			byte[] resultByteArray = messageDigest.digest();
			// 同样，把字节数组转换成字符串
			return byteArrayToHex(resultByteArray);

		} catch (NoSuchAlgorithmException e) {
			return null;
		} finally {
			try {
				digestInputStream.close();
			} catch (Exception e) {
			}
			try {
				fileInputStream.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 检查是否为合法图片
	 *
	 * @param multipartRequest
	 * @param formatList
	 *            图片格式list
	 * @return 是否合法
	 */
	public static boolean checkUploadImg(
			MultipartHttpServletRequest multipartRequest,
			List<String> formatList) {
		boolean isValidImg = false;

		// 文件对象
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
				.getFile("imgFile");

		if (file == null) {
			return isValidImg;
		}

		// 获得文件名(全路径)
		String realFileName = file.getOriginalFilename();
		String suffix = realFileName.substring(realFileName.lastIndexOf("."),
				realFileName.length());

		if (suffix == null) {
			return isValidImg;
		}

		List<String> imgFormat = new ArrayList<String>();
		if (formatList == null) {
			imgFormat.add(".jpg");
			imgFormat.add(".jpeg");
			imgFormat.add(".bmp");
			imgFormat.add(".png");
			imgFormat.add(".gif");
		}

		// 检查文件格式为图片
		for (String format : imgFormat) {
			if (suffix != null && suffix.trim().toLowerCase().equals(format)) {
				isValidImg = true;
				break;
			}
		}
		return isValidImg;
	}

	/**
	 *
	 * 检查上传文件大小
	 *
	 * @param multipartRequest
	 * @param limitSize
	 *            限制文件大小(单位:MB)
	 * @return
	 *
	 */
	public static boolean checkUploadFileSize(
            MultipartHttpServletRequest multipartRequest, long limitSize) {
		boolean isValidSize = false;

		// 文件对象
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
				.getFile("file");
		if (file == null) {
			file = (CommonsMultipartFile) multipartRequest.getFile("imgFile");
		}

		if (file == null) {
			return isValidSize;
		}

		long fileSize = file.getSize();
		if (limitSize > 0) {
			if (fileSize > 0 && fileSize <= (limitSize * 1024 * 1024)) {
				isValidSize = true;
			}
		} else {
			isValidSize = true;
		}
		return isValidSize;
	}

	/**
	 * 下载文件
	 *
	 * @param filePath
	 *            文件全路径包含文件名及文件格式
	 * @param fileName
	 *            完整文件名及格式
	 */
	public static void downloadFile(String filePath, String fileName,
                                    HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		File f = new File(filePath);

		if (!f.exists()) {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter()
					.print("<html><head><title>文件不存在</title></head><body>文件不存在！</body></html>");
			return;
		}

		ServletOutputStream out = response.getOutputStream();
		response.setHeader("Content-disposition", "attachment;filename="
				+ new String(fileName.getBytes("UTF-8"), "ISO_8859_1"));
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(filePath));
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}

	/**
	 * 字节流读取文件内容
	 *
	 * @throws IOException
	 */
	public static void inFile() throws IOException {
		String fileName = "E:/文件上传与下载.doc";
		File f = new File(fileName);
		FileInputStream in = new FileInputStream(f);
		byte[] b = new byte[1024];
		while (in.read() != -1) {
			in.read(b);
		}
		in.close();
		System.out.println(new String(b));
	}

	// 输入文件到本地
	public static void outFile() throws IOException {
		String fileName = "E:/sang.txt";
		File f = new File(fileName);
		FileOutputStream out = new FileOutputStream(f, true);
		String str = "\r\ntool";
		byte[] b = str.getBytes();
		for (int i = 0; i < b.length; i++) {
			out.write(b[i]);
		}
		out.close();
	}

	/**
	 * 读取本地文件，将其中内容复制到本地另一份文件中
	 *
	 * @throws IOException
	 */
	public static void copeFile() throws IOException {
		String inFile = "E:/sang.txt";
		File inputFile = new File(inFile);

		// 在系统的E盘创建文件夹 myBook1/myBook2 创建文件名 book.txt
		String directory = "E:/myBook1/myBook2";
		String fileName = "book.txt";
		// 在内存中创建一个文件对象,此时还没有在硬盘对应目录下创建实实在在的文件
		File f = new File(directory, fileName);
		if (f.exists()) {
			// 文件已经存在
			System.out.println(f.getAbsolutePath());
			System.out.println(f.getName());
			System.out.println(f.length());
		} else {
			// 先创建文件所在的目录
			boolean mkdirs = f.getParentFile().mkdirs();
			boolean createNewFile = f.createNewFile();
		}

		FileInputStream fileInputStream = new FileInputStream(inputFile);
		FileOutputStream fileOutputStream = new FileOutputStream(f);
		int temp = 0;
		while ((temp = fileInputStream.read()) != -1) {
			char ch = (char) temp;
			fileOutputStream.write(Character.toLowerCase(ch));
		}
		String outStr = fileOutputStream.toString();
		fileInputStream.close();
		fileOutputStream.close();
		System.out.println(outStr.toString());
	}

	/**
	 * 生产文件 如果文件所在路径不存在则生成路径
	 *
	 * @param fileName
	 *            文件名 带路径
	 * @param isDirectory
	 *            是否为路径
	 * @return
	 * @author yayagepei
	 * @date 2008-8-27
	 */
	public static File buildFile(String fileName, boolean isDirectory) {
		File target = new File(fileName);
		if (isDirectory) {
			target.mkdirs();
		} else {
			if (!target.getParentFile().exists()) {
				target.getParentFile().mkdirs();
				target = new File(target.getAbsolutePath());
			}
		}
		return target;
	}

}

