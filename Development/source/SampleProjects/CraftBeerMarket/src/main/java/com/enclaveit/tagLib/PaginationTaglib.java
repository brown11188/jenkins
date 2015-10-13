package com.enclaveit.tagLib;

import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author Ace
 *
 */
public class PaginationTaglib extends SimpleTagSupport{
    private String uri;
    private int offset;
    private int count;
    private int max = 10;
    private int steps = 10;
    private String previous = "Previous";
    private String next = "Next";
    private Writer getWriter(){
        JspWriter out = getJspContext().getOut();
        return out;
    }
    
    @Override
    public void doTag() throws JspException{
        Writer out = getWriter();
        try {
            out.write("<nav>");
            out.write("<ul class=\"pagination\">");
            if(offset < steps)
                out.write(constructLink(1, previous, "disabled", true));
            else
                out.write(constructLink(offset-steps, previous, null, false));
            
            for(int itr=0; itr<count; itr+=steps){
                if(offset==itr)
                    out.write(constructLink((itr/10+1)-1 *steps, String.valueOf(itr/10+1), "active", true));
                else
                    out.write(constructLink(itr/10*steps, String.valueOf(itr/10+1), null , false));
            }
            if(offset+steps>count)
                out.write(constructLink(offset+steps, next, "disabled", true));
               else
                out.write(constructLink(offset+steps, next, null , false));
                
                
               out.write("</ul>");
               out.write("</nav>"); 
        } catch (java.io.IOException ex) {
            throw new JspException("Error in Paginator tag", ex);
        }
    }
    
    private String constructLink(int page, String text, String className, boolean disabled){
        StringBuilder link = new StringBuilder("<li");
        if(className != null){
            link.append(" class=\"");
            link.append(className);
            link.append("\"");
        }
        if(disabled)
            link.append(">").append("<a href=\"#\">"+text+"</a></li>");
        else
            link.append(">").append("<a href=\""+uri+"?offset="+page + "\">"+text+"</a></li>");
        return link.toString();
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @return the steps
     */
    public int getSteps() {
        return steps;
    }

    /**
     * @param steps the steps to set
     */
    public void setSteps(int steps) {
        this.steps = steps;
    }

    /**
     * @return the previous
     */
    public String getPrevious() {
        return previous;
    }

    /**
     * @param previous the previous to set
     */
    public void setPrevious(String previous) {
        this.previous = previous;
    }

    /**
     * @return the next
     */
    public String getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(String next) {
        this.next = next;
    }
    
}
