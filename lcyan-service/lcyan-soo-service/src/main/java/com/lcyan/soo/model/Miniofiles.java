package com.lcyan.soo.model;

import lombok.Data;

@Data
public class Miniofiles {
	  private String etag; // except DeleteMarker

	  private String objectName;

	  //private ResponseDate lastModified;

	  private String id;
	  
	  private String DisplayName;

	  private long size; // except DeleteMarker

	  private String storageClass; // except DeleteMarker

	  private boolean isLatest; // except ListObjects V1

	  private String versionId; // except ListObjects V1

	  //private Metadata userMetadata;

	  private boolean isDir = true;
}
