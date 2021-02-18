package com.study.my.model;

public class Diploma {

    private Integer id;
    private Integer math;
    private Integer physics;
    private Integer history;
    private Integer literature;
    private Integer chemistry;
    private Integer biology;

    public Diploma(Integer id, Integer math, Integer physics, Integer history, Integer literature, Integer chemistry, Integer biology, Integer userId) {
        this.id = id;
        this.math = math;
        this.physics = physics;
        this.history = history;
        this.literature = literature;
        this.chemistry = chemistry;
        this.biology = biology;
        this.userId = userId;
    }

    private Integer userId;

    public Diploma() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMath() {
        return math;
    }

    public void setMath(Integer math) {
        this.math = math;
    }

    public Integer getPhysics() {
        return physics;
    }

    public void setPhysics(Integer physics) {
        this.physics = physics;
    }

    public Integer getHistory() {
        return history;
    }

    public void setHistory(Integer history) {
        this.history = history;
    }

    public Integer getLiterature() {
        return literature;
    }

    public void setLiterature(Integer literature) {
        this.literature = literature;
    }

    public Integer getChemistry() {
        return chemistry;
    }

    public void setChemistry(Integer chemistry) {
        this.chemistry = chemistry;
    }

    public Integer getBiology() {
        return biology;
    }

    public void setBiology(Integer biology) {
        this.biology = biology;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public static DiplomaBuilder builder() {
        return new DiplomaBuilder();
    }

    public static class DiplomaBuilder {
        private Integer id;
        private Integer math;
        private Integer physics;
        private Integer history;
        private Integer literature;
        private Integer chemistry;
        private Integer biology;
        private Integer userId;

        DiplomaBuilder() {
        }

        public DiplomaBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public DiplomaBuilder math(Integer math) {
            this.math = math;
            return this;
        }

        public DiplomaBuilder physics(Integer physics) {
            this.physics = physics;
            return this;
        }

        public DiplomaBuilder history(Integer history) {
            this.history = history;
            return this;
        }

        public DiplomaBuilder literature(Integer literature) {
            this.literature = literature;
            return this;
        }

        public DiplomaBuilder chemistry(Integer chemistry) {
            this.chemistry = chemistry;
            return this;
        }

        public DiplomaBuilder biology(Integer biology) {
            this.biology = biology;
            return this;
        }

        public DiplomaBuilder userId(Integer userId) {
            this.userId = userId;
            return this;
        }


        public Diploma build() {
            return new Diploma(id, math, physics, history, literature, chemistry, biology, userId);
        }

        public String toString() {
            return "Diploma.DiplomaBuilder(id=" + this.id + ", math=" + this.math + ", physics=" + this.physics + ", history=" + this.history + ", literature=" + this.literature + ", chemistry=" + this.chemistry + ", biology=" + this.biology + ")";
        }
    }

    @Override
    public String toString() {
        return "Diploma{" +
                "id=" + id +
                ", math=" + math +
                ", physics=" + physics +
                ", history=" + history +
                ", literature=" + literature +
                ", chemistry=" + chemistry +
                ", biology=" + biology +
                '}';
    }
}
