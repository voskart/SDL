<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/stones">
  <html>
  <body>
    <h1>Steine:</h1>
    <table border="1">
      <tr>
        <th>Titel</th>
        <th>Fundort</th>
        <th>Kommentar</th>
        <th>Maße</th>
      </tr>
      <xsl:for-each select="stone">
      <tr>
        <td><xsl:value-of select="Titel" /></td>
        <td><xsl:value-of select="Fundort" /></td>
        <td><xsl:value-of select="Kommentar" /></td>
        <td><xsl:value-of select="Masze" /></td>
      </tr>
      </xsl:for-each>
    </table> 
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>
