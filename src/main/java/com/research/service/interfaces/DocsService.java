package com.research.service.interfaces;

import com.research.dto.project.DocsDTO;
import com.research.entity.Docs;
import com.research.service.BaseService;

public interface DocsService extends BaseService<Docs>{

	Docs addNewDoc(DocsDTO docDTO);
}
