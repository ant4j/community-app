package app.community.content.controller;

import app.community.content.controller.model.ContentListDTO;
import app.community.content.controller.model.ContentParamDTO;
import app.community.content.controller.model.ContentTextDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.community.content.controller.model.ContentTextParamDTO;
import app.community.content.handler.ContentHandler;

//TODO sistemare cross origin
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ContentController {
	private static final Logger LOG = LoggerFactory.getLogger(ContentController.class);

	@Autowired
	private ContentHandler contentHandler;

	@GetMapping("/contents/{collectionId}")
	public @ResponseBody ResponseEntity<ContentListDTO> getContents(ContentParamDTO contentParamDTO) {
		LOG.info("getContents");
		ContentListDTO contentListDTO = contentHandler.getContents(contentParamDTO);
		return new ResponseEntity<ContentListDTO>(contentListDTO, HttpStatus.OK);
	}

	@GetMapping("/content/text/{contentId}/{collectionId}")
	public @ResponseBody ResponseEntity<ContentTextDTO> getContentText(ContentTextParamDTO contentTextParamDTO) {
		LOG.info("getContent");
		ContentTextDTO contentTextDTO = contentHandler.getContentText(contentTextParamDTO);
		return new ResponseEntity<ContentTextDTO>(contentTextDTO, HttpStatus.OK);
	}

}
