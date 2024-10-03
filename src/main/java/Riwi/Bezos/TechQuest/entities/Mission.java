package Riwi.Bezos.TechQuest.entities;

import Riwi.Bezos.TechQuest.enums.Difficulty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Getter
@Setter
public class Mission extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Difficulty difficulty;

    @ManyToMany
    @JoinTable(
            name = "mission_skills",
            joinColumns = @JoinColumn(name = "mission_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> mission_skills;

}
