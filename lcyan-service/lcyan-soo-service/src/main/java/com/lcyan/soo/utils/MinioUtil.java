package com.lcyan.soo.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lcyan.soo.model.Miniofiles;

import io.minio.BucketExistsArgs;
import io.minio.DownloadObjectArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListObjectsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveBucketArgs;
import io.minio.RemoveObjectArgs;
import io.minio.RemoveObjectsArgs;
import io.minio.Result;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;

/**
 * 
 * @author ayan2070
 *
 */
@Component
public class MinioUtil {

	@Autowired
	private MinioClient minioClient;

	/**
	 * 判断存储桶是否存在
	 */
	public Boolean bucketExists(String path) {

		try {
			return minioClient.bucketExists(BucketExistsArgs.builder().bucket(path).build());
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * 列出所有存储桶的存储桶信息
	 */
	public List<Bucket> getListBucket() {
		try {
			return minioClient.listBuckets();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 创建 bucket
	 * 
	 * @param bucketName
	 */
	public void makeBucket(String bucketName) {
		try {
			boolean isExist = bucketExists(bucketName);
			if (!isExist) {
				minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除空的bucket
	 */
	public void removeBucket(String bucketName) {
		try {
			boolean isExist = bucketExists(bucketName);
			if (isExist)
				minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传文件
	 */
	public void putObject(String bucketName, String objectName, InputStream inputStream, String fileType) {
		try {
			minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName)
					.stream(inputStream, -1, 10485760).contentType(fileType).build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	
	public List<Miniofiles> listObjects(String bucketName) {
		try {
			Iterable<Result<Item>> results = minioClient.listObjects(
				    ListObjectsArgs.builder().bucket(bucketName).recursive(true).build());
			List<Miniofiles> miniofiless = new ArrayList<>();
			results.forEach(result ->{
				try {
					Miniofiles miniofiles = new Miniofiles();
					Item item = result.get();
					miniofiles.setObjectName(item.objectName());
					miniofiles.setSize(item.size());
					miniofiles.setEtag(item.etag());
					miniofiles.setId(item.owner().id());
					miniofiles.setDisplayName(item.owner().displayName());
					miniofiles.setDir(item.isDir());
					miniofiles.setLatest(item.isLatest());
					miniofiles.setVersionId(item.versionId());
					miniofiles.setStorageClass(item.storageClass());
					miniofiless.add(miniofiles);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			});
			return miniofiless;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 获取文件数据流
	 */
	public InputStream getObject(String bucketName, String objectName) {
		try {
			return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 下载文件
	 */
	public void download(String bucketName, String objectName, String fileName) {
		try {
			minioClient.downloadObject(
					DownloadObjectArgs.builder().bucket(bucketName).object(objectName).filename(fileName).build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取文件URL
	 */
	public String getObjectUrl(String bucketName, String objectName) {
		try {
			return minioClient.getObjectUrl(bucketName, objectName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取文件URL,有过期时间
	 */
	public String getObjectUrl(String bucketName, String objectName, int time) {
		try {
			return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET)
					.bucket(bucketName).object(objectName).expiry(time).build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objectName;
	}

	/**
	 * 删除文件
	 */
	public void removeObject(String bucketName, String objectName) {
		try {
			// Remove object.
			minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 批量删除
	 */
	public void removeAll(String bucketName, List<String> objectNames) {
		try {
			List<DeleteObject> objects = new LinkedList<>();
			objectNames.forEach(objectName -> {
				objects.add(new DeleteObject(objectName));
			});
			Iterable<Result<DeleteError>> results = minioClient
					.removeObjects(RemoveObjectsArgs.builder().bucket(bucketName).objects(objects).build());
			for (Result<DeleteError> result : results) {
				result.get();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
