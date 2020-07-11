package com.leodelmiro.course.services;

import com.leodelmiro.course.entities.Category;
import com.leodelmiro.course.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;
    @InjectMocks
    private CategoryService service;

    private List<Category> categories = new ArrayList<>();
    private Category category1;
    private Category category2;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        category1 = new Category(1L, "Books");
        category2 = new Category(2L, "Games");

        when(repository.findAll()).thenReturn(categories);
        when(repository.findById(anyLong())).thenReturn(null);
        when(repository.findById(1L)).thenReturn(Optional.of(category1));
        when(repository.findById(2L)).thenReturn(Optional.of(category2));
    }

    @Test
    public void findAll_ShouldReturnCategories_whenCategoriesNotNull() {
        categories.add(category1);
        categories.add(category2);
        List<Category> categoriesFindAll = service.findAll();

        assertEquals(categories, categoriesFindAll);
    }

    @Test
    public void findAll_ShouldReturnNull_whenCategoriesNull() {
        categories = Collections.emptyList();
        List<Category> categoriesFindAll = service.findAll();

        assertEquals(categories, categoriesFindAll);
    }

    @Test
    public void findById_ShouldReturnCategory_whenCategoryIdExist() {
        Category categoryTest1 = service.findById(1L);
        Category categoryTest2 = service.findById(2L);

        assertEquals(category1, categoryTest1);
        assertEquals(category2, categoryTest2);
    }

    @Test
    public void findById_ShouldThrowNullPointerException_whenCategoryIdNotExist() {
        assertThrows(NullPointerException.class, () -> service.findById(3L));
    }
}
