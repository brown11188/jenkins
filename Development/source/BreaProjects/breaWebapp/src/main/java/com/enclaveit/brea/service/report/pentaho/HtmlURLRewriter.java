package com.enclaveit.brea.service.report.pentaho;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.pentaho.reporting.engine.classic.core.modules.output.table.html.URLRewriteException;
import org.pentaho.reporting.engine.classic.core.modules.output.table.html.URLRewriter;
import org.pentaho.reporting.libraries.repository.ContentEntity;
import org.pentaho.reporting.libraries.repository.ContentLocation;

public class HtmlURLRewriter implements URLRewriter {

    private String pattern;
    private boolean useContentIdAsName;

    public HtmlURLRewriter(final String pattern, boolean useContentIdAsName) {
        this.pattern = pattern;
        this.useContentIdAsName = useContentIdAsName;
    }

    @Override
    public String rewrite(final ContentEntity contentEntry, final ContentEntity dataEntity) throws URLRewriteException {
        final List<String> entityNames = new ArrayList<String>();
        entityNames.add(useContentIdAsName ? dataEntity.getContentId().toString() : dataEntity.getName());

        ContentLocation location = dataEntity.getParent();
        while (location != null) {
            entityNames.add(location.getName());
            location = location.getParent();
        }

        final StringBuilder b = new StringBuilder();
        for (int i = entityNames.size() - 1; i >= 0; i--) {
            final String name = entityNames.get(i);
            if (i == 0) {
                b.append(name);
            }
        }

        if (pattern == null) {
            return b.toString();
        }

        return MessageFormat.format(pattern, new Object[]{b.toString()});
    }
}
