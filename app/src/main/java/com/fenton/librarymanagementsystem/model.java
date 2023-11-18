package com.fenton.librarymanagementsystem;

public class model {
    String bookid, bookname, authorname;

    public model(String bookid, String bookname, String authorname) {
        this.bookid = bookid;
        this.bookname = bookname;
        this.authorname = authorname;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }
}
