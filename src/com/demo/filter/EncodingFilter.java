package com.demo.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@WebFilter(urlPatterns = "/*")
public class EncodingFilter implements Filter {

    private String encoding = null;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        //解决响应乱码
        response.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=" + encoding);
        chain.doFilter(new MyHttpServletRequest((HttpServletRequest) request), response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getServletContext().getInitParameter("encoding");
        if (encoding == null) {
            encoding = "utf-8";
        }
    }

    class MyHttpServletRequest extends HttpServletRequestWrapper {

        private HttpServletRequest request;
        private boolean isEncoded = false;

        MyHttpServletRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        @Override
        public String getParameter(String name) {
            String[] values = getParameterMap().get(name);
            return values == null ? null : values[0];
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            try {
                if ("POST".equalsIgnoreCase((request.getMethod()))) {
                    request.setCharacterEncoding(encoding);
                    return request.getParameterMap();
                } else if ("GET".equalsIgnoreCase(request.getMethod())) {
                    if (!isEncoded) {
                        Map<String, String[]> map = request.getParameterMap();
                        for (Map.Entry<String, String[]> me : map.entrySet()) {
                            String[] values = me.getValue();
                            for (int i = 0; i < values.length; i++) {
                                values[i] = URLDecoder.decode(values[i], encoding);
                            }
                        }
                        isEncoded = true;
                        return map;
                    } else {
                        return request.getParameterMap();
                    }
                } else {
                    return request.getParameterMap();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        @Override
        public String[] getParameterValues(String name) {
            return getParameterMap().get(name);
        }
    }
}
