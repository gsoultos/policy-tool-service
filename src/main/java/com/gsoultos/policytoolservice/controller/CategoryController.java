package com.gsoultos.policytoolservice.controller;

import com.gsoultos.policytoolservice.dto.validator.CategoryValidator;
import com.gsoultos.policytoolservice.service.CategoryTemplate;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
  private final Map<String, CategoryTemplate> categoryServices;

  @Autowired
  public CategoryController(Map<String, CategoryTemplate> categoryServices) {
    this.categoryServices = categoryServices;
  }

  @PostMapping("/{category}")
  void addAttribute(
      @PathVariable @CategoryValidator String category, @RequestBody Attribute attribute) {
    categoryServices.get(category).addAttribute(attribute);
  }

  @GetMapping("/{category}")
  Map<String, Attribute> getAttributes(@PathVariable @CategoryValidator String category) {
    return categoryServices.get(category).getAttributes();
  }

  @GetMapping("/{category}/{attributeId}")
  Attribute getAttribute(
      @PathVariable @CategoryValidator String category,
      @PathVariable String attributeId) {
    return categoryServices.get(category).getAttribute(attributeId);
  }

  @DeleteMapping("/{category}/{attributeId}")
  void deleteAttribute(
      @PathVariable @CategoryValidator String category,
      @PathVariable String attributeId) {
    categoryServices.get(category).deleteAttribute(attributeId);
  }
}
