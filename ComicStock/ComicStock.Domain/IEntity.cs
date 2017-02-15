namespace ComicStock.Data
{
    public interface IEntity<TKey>
    {
        bool IsDeleted { get; }
    }
}