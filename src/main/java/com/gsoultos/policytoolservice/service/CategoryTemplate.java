package com.gsoultos.policytoolservice.service;

import lombok.NonNull;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.Attribute;
import org.ow2.authzforce.sdk.core.schema.category.Category;

import java.util.*;

public class CategoryTemplate {
  protected final Map<String, Attribute> attributes;
  protected final Category category;

  public CategoryTemplate(@NonNull Category category) {
    this.attributes = new HashMap<>();
    this.category = category;
  }

  public final void addAttribute(@NonNull Attribute attribute) {
    this.attributes.put(attribute.getAttributeId(), attribute);
  }

  public final Attribute getAttribute(@NonNull String attributeId) {
    return this.attributes.get(attributeId);
  }

  public final Map<String, Attribute> getAttributes() {
    return attributes;
  }

  public final void deleteAttribute(@NonNull String attributeId) {
    this.attributes.remove(attributeId);
  }

  public final void deleteAllAttributes() {
    this.attributes.clear();
  }

  public final Category getCategory() {
    return category;
  }
}
