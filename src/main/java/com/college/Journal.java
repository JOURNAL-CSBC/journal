package com.journal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/* Колекція, в якій зберігається документ баз даних MongoDB, що представляє сутність рядку з таблиці Журналу.
*/
@Document(collection = "Journal")
public class Journal {
    @Id
    private String id;
    private String journalistName;
    private String editorName;
    private String readerName;
    private String publicationDate;
    private String articleTitle;
    private String category;
    private String editorComments;
    private double articleRating;
    private String editorialAddress;
    private String editorialPhone;

    // Конструктор за замовчуванням (потрібен для Spring Data / MongoDB)
    public Journal() {
    }

    // Конструктор з параметрами для створення об'єктів
    public Journal(
        String journalistName,
        String editorName,
        String readerName,
        String publicationDate,
        String articleTitle,
        String category,
        String editorComments,
        double articleRating,
        String editorialAddress,
        String editorialPhone
    ) {
        this.journalistName = journalistName;
        this.editorName = editorName;
        this.readerName = readerName;
        this.publicationDate = publicationDate;
        this.articleTitle = articleTitle;
        this.category = category;
        this.editorComments = editorComments;
        this.articleRating = articleRating;
        this.editorialAddress = editorialAddress;
        this.editorialPhone = editorialPhone;
    }

    // Геттери та сеттери (необхідні для коректної роботи Spring Data MongoDB)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJournalistName() {
        return journalistName;
    }

    public void setJournalistName(String journalistName) {
        this.journalistName = journalistName;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEditorComments() {
        return editorComments;
    }

    public void setEditorComments(String editorComments) {
        this.editorComments = editorComments;
    }

    public double getArticleRating() {
        return articleRating;
    }

    public void setArticleRating(double articleRating) {
        this.articleRating = articleRating;
    }

    public String getEditorialAddress() {
        return editorialAddress;
    }

    public void setEditorialAddress(String editorialAddress) {
        this.editorialAddress = editorialAddress;
    }

    public String getEditorialPhone() {
        return editorialPhone;
    }

    public void setEditorialPhone(String editorialPhone) {
        this.editorialPhone = editorialPhone;
    }
    
    @Override
    public String toString() {
        return "Journal {" + "\n" +
                " id=\"" + id + "\"\n" +
                " journalistName=\"" + journalistName + "\"\n" +
                " editorName=\"" + editorName + "\"\n" +
                " readerName=\"" + readerName + "\"\n" +
                " publicationDate=\"" + publicationDate + "\"\n" +
                " articleTitle=\"" + articleTitle + "\"\n" +
                " category=\"" + category + "\"\n" +
                " editorComments=\"" + editorComments + "\"\n" +
                " articleRating=" + articleRating + "\n" +
                " editorialAddress=\"" + editorialAddress + "\"\n" +
                " editorialPhone=\"" + editorialPhone + "\"\n" +
                "}";
    }
}