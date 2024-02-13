package hu.szakdolgozat.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@MappedSuperclass
public abstract class CoreEntity {

    @Id
    @Column(name ="ID")
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    private Long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, name = "creation_date")
    private Date creationDate;

    @Column(name = "modification_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;

    @ManyToOne
    @JoinColumn(name = "creator_user",nullable = false)
    private User creatorUser;

    @ManyToOne
    @JoinColumn(name = "modifier_user")
    private User modifierUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }

    public User getModifierUser() {
        return modifierUser;
    }

    public void setModifierUser(User modifierUser) {
        this.modifierUser = modifierUser;
    }
}
