/**
 * 接受表单的对象
 */
package com.example.demo.form;

import com.example.demo.entity.AdminBean;
import org.springframework.beans.BeanUtils;


public class AdminForm {

    private String uname;

    private String upass;

    private String usex;

    private String uemil;
    private  String yzm;

    public AdminForm() {

    }

    @Override
    public String toString() {
        return "AdminForm{" +
                "uname='" + uname + '\'' +
                ", upass='" + upass + '\'' +
                ", usex='" + usex + '\'' +
                ", uemil='" + uemil + '\'' +
                ", yzm='" + yzm + '\'' +
                '}';
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public String getUemil() {
        return uemil;
    }

    public void setUemil(String uemil) {
        this.uemil = uemil;
    }

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
    }

    /**
     *
     * @param
     * @return
     */
    public AdminBean convertAdminBean(){
        return new AdminFormConvert().convert(this);
    }
    private class AdminFormConvert implements FormConvert<AdminForm,AdminBean> {
        @Override
        public AdminBean convert(AdminForm adminForm) {
            AdminBean adminBean = new AdminBean();
            //复制对象属性
            BeanUtils.copyProperties(adminForm,adminBean);
            return adminBean;
        }
    }

}
