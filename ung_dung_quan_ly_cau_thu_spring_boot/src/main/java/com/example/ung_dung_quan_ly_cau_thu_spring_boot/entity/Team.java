    package com.example.ung_dung_quan_ly_cau_thu_spring_boot.entity;


    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Entity(name = "teams")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Team {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String name;

    }
