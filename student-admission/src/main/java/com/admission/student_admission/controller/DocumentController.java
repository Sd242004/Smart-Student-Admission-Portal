package com.admission.student_admission.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.admission.student_admission.entity.Document;
import com.admission.student_admission.service.DocumentService;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<Document> saveDocument(
            @RequestBody Document document) {

        return ResponseEntity.ok(
                documentService.saveDocument(document)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentById(
            @PathVariable Long id) {

        Document document =
                documentService.getDocumentById(id);

        if (document == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(document);
    }
}