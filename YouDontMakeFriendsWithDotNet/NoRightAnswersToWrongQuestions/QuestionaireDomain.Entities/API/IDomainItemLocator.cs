using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IDomainItemLocator
    {
        TDomainItem Get<TDomainItem>(Guid id) where TDomainItem : IDomainItem;
        IEnumerable<TDomainItem> GetAll<TDomainItem>() where TDomainItem : IDomainItem;

        Reference<TDomainItem> GetRef<TDomainItem>(Guid id) where TDomainItem : IDomainItem;
        IEnumerable<Reference<TDomainItem>> GetAllRefs<TDomainItem>() where TDomainItem : IDomainItem;

        bool Exists<TDomainItem>(Guid id) where TDomainItem : IDomainItem;
    }
}