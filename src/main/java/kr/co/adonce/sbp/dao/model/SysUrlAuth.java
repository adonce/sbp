package kr.co.adonce.sbp.dao.model;

import java.util.HashSet;
import java.util.Set;

import open.commons.annotation.ColumnDef;

/**
 * 권한 관리 URL 모델
 * @author jhlee
 *
 */
public class SysUrlAuth {
  
  /**
   * URL 메소드
   */
  private String method;
  
  /**
   * URL 패턴
   */
  private String pattern;
  
  /**
   * URL
   */
  private String url;
  
  /**
   * URL 권한
   */
  private String grade;
  
  /**
   * 사용되는 메뉴
   */
  private String menu;
  
  private Set<String> grades = new HashSet<>();
  
  /**
   * URL 설명
   */
  private String desc;
  
  public SysUrlAuth() {
  }

  public Set<String> getGrades() {
    return grades;
  }
  
  public void addGrade(String grade){
    if (grade != null) {
      this.grades.add(grade);
    }
  }
  
  public void setGrades(Set<String> grades) {
    this.grades = grades;
  }

  public String isDesc() {
    return desc;
  }

  public String getGrade() {
    return grade;
  }

  public String getMenu() {
    return menu;
  }

  public String getMethod() {
    return method;
  }

  public String getPattern() {
    return pattern;
  }

  public String getUrl() {
    return url;
  }
  
  @ColumnDef(name="desc", type=String.class, caseSensitive=false)
  public void setDesc(String desc) {
    this.desc = desc;
  }

  @ColumnDef(name="grade", type=String.class, caseSensitive=false)
  public void setGrade(String grade) {
    this.grade = grade;
  }

  @ColumnDef(name="menu", type=String.class, caseSensitive=false)
  public void setMenu(String menu) {
    this.menu = menu;
  }

  @ColumnDef(name="method", type=String.class, caseSensitive=false)
  public void setMethod(String method) {
    this.method = method;
  }

  @ColumnDef(name="pattern", type=String.class, caseSensitive=false)
  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  @ColumnDef(name="url", type=String.class, caseSensitive=false)
  public void setUrl(String url) {
    this.url = url;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "SysUrlAuth [method=" + method + ", pattern=" + pattern + ", url=" + url + ", grade="
        + grade + ", menu=" + menu + ", grades=" + grades + ", desc=" + desc + "]";
  }

}
