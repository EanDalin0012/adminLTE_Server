package com.spring.adminlte.api.restcontroller.admin;

import com.spring.adminlte.constants.BizResultCodeType;
import com.spring.adminlte.constants.ChannelTypeCode;
import com.spring.adminlte.constants.LangaugeCode;
import com.spring.adminlte.constants.SYN;
import com.spring.adminlte.dao.ProductImageDao;
import com.spring.adminlte.dto.*;
import com.spring.adminlte.services.serviceImplements.FileSystemStorageService;
import com.spring.adminlte.services.serviceImplements.ResourceFileInfoServiceImplement;
import com.spring.adminlte.core.template.classes.DataResponse;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/file")
public class FileRestController {
    private static final Logger log = LoggerFactory.getLogger(CompanyRestController.class);

    @Autowired
    private FileSystemStorageService fileSystemStorageService;
    @Autowired
    private ProductImageDao productImageService;
    public FileResponseDto store() {
        return null;
    }

    @Autowired
    private ResourceFileInfoServiceImplement resourceFileInfoService;
    /*
    * @functionName getImage
    * @description get image by path
    * */
    @GetMapping(value = "/logo/{id}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable("id") int id) throws IOException {

        try{
//            ProductImageDto productImageDto = productImageService.getValueById(id);
            String resource = productImageService.getPathImage(id);
//            String resource = productImageDto.getUri();
            ClassPathResource imgFile = new ClassPathResource(resource);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new InputStreamResource(imgFile.getInputStream()));
        }catch (Exception e) {
            throw e;
        }
    }
    @PostMapping(value = "/image")
    public ResponseEntity<DataResponse<ImageDto>> store(@RequestBody MultipartFile file) {
        DataResponse<ImageDto> response = new DataResponse<>();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/upload/product")
    public DataResponse<ImageURLDto> handleFileUpload(@RequestParam("file") MultipartFile multipartFile,
                                                      @RequestParam("fileImageURL") String fileImageURL,
                                                      @RequestParam("userID") String userID,
                                                      @RequestParam("productId") String productId) throws IOException {
        DataResponse<ImageURLDto> response = new DataResponse<>();
        InputStream is = null;
        HeaderDto header = new HeaderDto();
        ImageURLDto imageUrl = new ImageURLDto();
        try {
            boolean file = multipartFile.isEmpty();
            if ( !file ) {
                ResourceFileInfoDto profile = new ResourceFileInfoDto();
                UUID uuid = UUID.randomUUID();
                String resID = uuid.toString();
                String[] originalFilename = multipartFile.getOriginalFilename().split("\\.(?=[^\\.]+$)");

                profile.setFileTypeCode(BizResultCodeType.FILE_TYPE_CODE.getValue());
                profile.setFileName(originalFilename[0]);
                profile.setFileExt(originalFilename[1]);
                profile.setFileContentType(multipartFile.getContentType());

                profile.setFileSize(multipartFile.getSize());
                profile.setCreatedBy(userID);
                profile.setUpdatedBy(userID);
                profile.setFileImageURL(fileImageURL);

                is = multipartFile.getInputStream();
                profile.setFileData(IOUtils.toByteArray(is));
                IOUtils.closeQuietly(is);
                profile.setId( resID );
                int add = resourceFileInfoService.addCompanyProfile( profile );

                if (add > 0) {
                    imageUrl.setId(profile.getId());
                    imageUrl.setImageURL(fileImageURL +"/images/resources/"+resID);
                    header.setResult( true );
                    header.setMsg( BizResultCodeType.RESPONSE_SUCCESS_CODE.getValue() );
                    header.setAuthData( BizResultCodeType.RESPONSE_SUCCESS_MESSAGE.getValue() );

                    response.setHeader( header );
                    response.setBody(imageUrl);
                    return response;
                }
//
//                if ( StringUtils.isEmpty( profile.getFileImageURL().trim() == "ad" ) || profile.getFileImageURL().equals("dsd"))  {
//                    // prepare data to update to m-banking db
//                    String[] preProfile = profile.getFileImageURL().split( "/" );
//                    resID = preProfile[preProfile.length - 1];
//                    profile.setId( resID );
//                    ResourceFileInfoDto resource = resourceFileInfoService.getResourceById( resID );
//
//                    if ( resource == null ) {
////                        resourceFileInfoService.addCompanyProfile( profile );
//                    } else {
////                        resourceFileInfoService.updateCompanyProfile( profile );
//                    }
//
//                } else {
//                    // prepare data to insert to m-banking db
//                    profile.setId( resID );
//                    resourceFileInfoService.addCompanyProfile( profile );
//                }
            }

        } catch (Exception e) {
            throw e;
        }

        header.setResult( false );
        header.setMsg( BizResultCodeType.RESPONSE_SUCCESS_CODE.getValue() );
        header.setAuthData( BizResultCodeType.RESPONSE_SUCCESS_MESSAGE.getValue() );
        response.setHeader( header );
        response.setBody(imageUrl);
        return response;
    }

    @GetMapping("/images/resources/{resID}")
    public ResponseEntity<byte[]> getCompanyProfile(@PathVariable("resID") String resID) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        byte[] imageDataBytes = null;
        try {
            ResourceFileInfoDto profile =  resourceFileInfoService.getResourceById(resID);
            if(profile != null && profile.getFileData().length > 0){
                imageDataBytes = profile.getFileData();
                if(imageDataBytes.length > 204800){ // resize image if it is bigger than 200kb
                    imageDataBytes = this.scale(imageDataBytes, profile.getFileExt());
                }

                String fileExt = profile.getFileExt();

                if( fileExt.equalsIgnoreCase("JPG") ) {
                    headers.setContentType(MediaType.IMAGE_JPEG);
                } else if( fileExt.equalsIgnoreCase("PNG") ) {
                    headers.setContentType(MediaType.IMAGE_PNG);
                } else {
                    headers.setContentType(MediaType.IMAGE_PNG);
                }

                headers.setContentLength(imageDataBytes.length);
            }
        } catch (Exception e) {
            log.error("Error view image : " + e.getMessage());
        }
        return new ResponseEntity<byte[]>(imageDataBytes, headers, HttpStatus.OK);
    }

    public byte[] scale(byte[] fileData,String ext) throws IOException {

        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        try {
            BufferedImage img = ImageIO.read(in);
            img = Scalr.resize(img, 600);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ImageIO.write(img, ext, buffer);
            return buffer.toByteArray();
        } catch (IOException e) {
            throw e;
        }
    }

    @PostMapping("/removeUrl")
    public DataResponse<ReturnYNDto> handleFileRemove(@RequestParam("resourceFileInfoId") String resourceFileInfoId) throws IOException {
        DataResponse<ReturnYNDto> response = new DataResponse<>();
        HeaderDto header = new HeaderDto();
        header.setMsg("");
        header.setChannelTypeCode(ChannelTypeCode.ADMIN.getKey());
        header.setAuthData("");
        header.setLanguageCode(LangaugeCode.EN.getKey());
        response.setBody(new ReturnYNDto(false, SYN.N.getKey()));
        try {
            if (resourceFileInfoId.isEmpty() || resourceFileInfoId.equals("")) {
                int delete = resourceFileInfoService.deleteById(resourceFileInfoId);
                if (delete > 0) {
                    header.setResult(true);
                    response.setHeader(header);
                    response.setBody(new ReturnYNDto(true, SYN.Y.getKey()));
                    return response;
                }
            }

        }catch (Exception e) {
            log.error("\n get error: handle file remove\n", e.getMessage());
            throw  e;
        }

        response.setHeader(header);
        return response;
    }

    }
