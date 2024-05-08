package com.GuruDev.Blog.Application.Model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(nullable = false, unique = true)
    private String categoryTitle;

    @Column(nullable = false)
    private String categoryDescription;

    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private List<Post> Post = new ArrayList<>();

}
