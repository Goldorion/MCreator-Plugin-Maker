{
  "id": "${settings.getModID()}",
  "weight": 0,
  "minversion": 202200225609,
  "info": {
    "name": "${settings.getModName()}",
    <#if settings.getDescription()?has_content>
        "description": "${settings.getDescription()}",
    </#if>
    <#if settings.getAuthor()?has_content>
        "author": "${settings.getAuthor()}",
    </#if>
    <#if settings.getCleanVersion()?has_content>
        "version": "${settings.getCleanVersion()}",
    </#if>
    <#if settings.getCredits()?has_content>
        "credits": "${settings.getCredits()}"
    </#if>
  }
}