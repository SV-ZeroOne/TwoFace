using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using System.Collections.Generic;
using System.Linq;

namespace ComicStock.Data.Implementations
{
    internal class CreatorRepo : EFRepository<Creator, int>, CreatorInterface
    {
        public override IEnumerable<Creator> Paging(int page, int pageSize)
        {
            return context.Set<Creator>()
                .OrderBy(x => x.CreatorID)
                .Skip((page - 1) * pageSize)
                .Take(pageSize)
                .ToList();
        }
    }
}
