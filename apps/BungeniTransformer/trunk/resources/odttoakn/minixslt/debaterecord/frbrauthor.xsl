<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output indent="yes" method="xml" encoding="UTF-8"/>

    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>

    <xsl:include href="../common/identity_template.xsl"/>

    <xsl:template match="*[@name='author']">
        <FRBRauthor>
            <xsl:if test="@href">
                <xsl:attribute name="href">
                    <xsl:value-of select="@href"/>
                </xsl:attribute>
            </xsl:if>
            <xsl:if test="@as">
                <xsl:attribute name="as">
                    <xsl:value-of select="@as"/>
                </xsl:attribute>
            </xsl:if>

            <xsl:apply-templates/>
        </FRBRauthor>
    </xsl:template>

    <xsl:include href="../common/normalize_text_template.xsl"/>

</xsl:stylesheet>
